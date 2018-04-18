package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.Connect.ConnectionService;
import by.gsu.epamlab.Connect.FactoryConnection;
import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.ifaces.ITicketDAO;
import by.gsu.epamlab.model.beans.*;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;
import javax.naming.NamingException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.*;

/**
 * Created by alex on 17.06.2016.
 */
public class DBTicketImpl implements ITicketDAO {
    private static final String STATUS_PAID = "paid";
    private static final String STATUS_NOT_PAID = "not_paid";
    public static final String PATH_FOR_REPT = "C:\\rept\\";


    public List<Ticket> addTicket(String[] ticketList) throws DaoException, DataBaseConnectionException {
        final int TICKETS_TABLE_ID_NAME = 1;
        final int TICKETS_TABLE_ID_SEAT = 2;
        final int TICKETS_TABLE_ID_CATEGORY= 3;
        final int TICKETS_TABLE_ID_DATE= 4;
        final int TICKETS_TABLE_ID_PREMIERE= 5;
        final int TICKETS_TABLE_ID_PRICE= 6;
        final int TICKETS_TABLE_ID_IS_PAID= 7;
        Connection cn = null;
        List<Ticket> ticketsAddInBase = new ArrayList<>();
        PreparedStatement ps = null;
            try{
                cn = FactoryConnection.getConnectionFromFactory();
                for(String ticket : ticketList) {
                    String[] ticketStr = ticket.split(";");
                    String buyer = ticketStr[0];
                    String seat = ticketStr[1];
                    String category = ticketStr[2];
                    String date = ticketStr[3];
                    String premiere = ticketStr[4];
                    String price = ticketStr[5];
                    Ticket ticketAdd = new Ticket(buyer, seat, category, date , premiere, price);
                    synchronized (DBTicketImpl.class){
                            if(isTicketBuy(ticketAdd.getNumber(),date,new Premiere(premiere),ticketAdd.getCategory())){
                                ps = cn.prepareStatement("INSERT INTO jeeproject.tickets(name , seat, category, date, premiere,price,isPaid )" +
                                        " values(?,?,?,?,?,?,?);");
                            ps.setString(TICKETS_TABLE_ID_NAME, ticketAdd.getName());
                            ps.setInt(TICKETS_TABLE_ID_SEAT, ticketAdd.getNumber());
                            ps.setString(TICKETS_TABLE_ID_CATEGORY, ticketAdd.getCategory());
                            ps.setDate(TICKETS_TABLE_ID_DATE, ticketAdd.getDate());
                            ps.setString(TICKETS_TABLE_ID_PREMIERE, ticketAdd.getPremiere());
                            ps.setInt(TICKETS_TABLE_ID_PRICE, ticketAdd.getPrice());
                            ps.setString(TICKETS_TABLE_ID_IS_PAID,STATUS_NOT_PAID);
                            ps.executeUpdate();
                            }
                            else new DaoException(Constants.ERROR_TICKET_PAID);
                    }
                    ticketsAddInBase.add(ticketAdd);
                }
            }
            catch(SQLException | NamingException e) {
                throw new DaoException(e.getMessage());
             }
            finally {
                ConnectionService.closeService(cn,ps);
            }
        return ticketsAddInBase;
    }

    @Override
    public boolean setPurchasedTicked(String[] id) throws DataBaseConnectionException, DaoException {
        Connection cn = null;
        Statement stmt = null;
        try{
            cn = FactoryConnection.getConnectionFromFactory();

            for(String s : id) {
                String sql = "UPDATE jeeproject.tickets " +
                        "SET isPaid ='" + STATUS_PAID + "' WHERE idTicket='" + s + "';";
                stmt = cn.createStatement();
                synchronized (DBTicketImpl.class){
                    stmt = cn.prepareStatement(sql);
                    stmt.execute(sql);
                }
            }
        } catch(SQLException | NamingException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            ConnectionService.closeService(cn,stmt);
        }
        return true;
    }

    @Override
    public boolean isTicketBuy(int seat, String date, Premiere premiere, String category) throws DataBaseConnectionException, DaoException {
        Statement st = null;
        ResultSet rs = null;
        boolean isTicketNew = true;
        Connection cn = null;
        String query = "select * from  jeeproject.tickets where seat='" + seat
                + "' AND date='" + date + "' AND premiere='" + premiere + "' and  category='"+
                category + "';";
        try {
            cn = FactoryConnection.getConnectionFromFactory();

            st = cn.createStatement();
            synchronized (DBUserImpl.class) {
                rs = st.executeQuery(query);
                if (rs.next()) {
                    isTicketNew = false;
                }
            }
            return isTicketNew;
        } catch(SQLException | NamingException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            ConnectionService.closeService(cn, st, rs);
        }

    }

    @Override
    public Seance showAviableTickets(String date, String prem, List<SeatClass> seats) throws DaoException, DataBaseConnectionException {
        final int TICKETS_TABLE_ID_SEAT = 2;
        final int TICKETS_TABLE_ID_CATEGORY= 3;
        final int TICKETS_TABLE_ID_DATE= 4;
        final int TICKETS_TABLE_ID_PREMIERE= 5;
        final int TICKETS_TABLE_ID_PRICE= 6;
        final int TICKETS_TABLE_ID_IS_PAID= 7;
        final int TICKETS_COUNT = 1;

        Connection cn = null;
        List<SeatClass> aviableSeats = new ArrayList<>();
        List<Ticket> purchasedTickets = new ArrayList<>();
        Seance sessionTheather = new Seance(date, prem, aviableSeats, purchasedTickets);
        Statement st = null;
        ResultSet rsSeats = null;
        ResultSet rsTickets = null;
        try {
            cn = FactoryConnection.getConnectionFromFactory();
            st = cn.createStatement();
            String queryTickets= "select * from jeeproject.tickets WHERE jeeproject.tickets.date = '" + date + "';";
            rsTickets = st.executeQuery(queryTickets);
            synchronized (DBTicketImpl.class) {
                while (rsTickets.next()) {
                    purchasedTickets.add(
                            new Ticket(
                                rsTickets.getString(TICKETS_TABLE_ID_SEAT),
                                rsTickets.getInt(TICKETS_TABLE_ID_CATEGORY),
                                rsTickets.getString(TICKETS_TABLE_ID_DATE),
                                rsTickets.getDate(TICKETS_TABLE_ID_PREMIERE),
                                rsTickets.getString(TICKETS_TABLE_ID_PRICE),
                                 rsTickets.getInt(TICKETS_TABLE_ID_IS_PAID)
                            )
                    );
                }
            }
            for(SeatClass seat : seats) {
                int amount = seat.getAmount();
                int price = seat.getPrice();
                String category = seat.getCategory();
                SeatClass t = new SeatClass(category, price,amount);
                String querySeats  = "select count(*) FROM jeeproject.tickets WHERE jeeproject.tickets.date = '" + date + "'" +
                    " AND jeeproject.tickets.category = '" + category + "';";
                 String queryS  = "select seat FROM jeeproject.tickets WHERE jeeproject.tickets.date = '" + date + "'" +
                    " AND jeeproject.tickets.category = '" + category + "';";
                synchronized (DBTicketImpl.class) {
                    rsSeats = st.executeQuery(querySeats);
                    if (rsSeats.next()) {
                        int countNumber = rsSeats.getInt(1);
                        int f = t.getAmount() - countNumber;
                        t.setAmount(f);
                    }
                    rsSeats = st.executeQuery(queryS);
                    while (rsSeats.next()) {
                        int r = rsSeats.getInt(TICKETS_COUNT);
                        t.deleteSeat(r);
                    }
                }
                aviableSeats.add(t);
            }
            }  catch(SQLException | NamingException e) {
            throw new DaoException(e.getMessage());
            }
            finally {
                ConnectionService.closeService(cn,st,rsSeats);
            }
        return sessionTheather;
    }
    @Override
    public List<TicketPurchased> getTickets(String date) throws DataBaseConnectionException, DaoException {
        final int TICKETS_TABLE_ID_ID_TICKET = 1;
        final int TICKETS_TABLE_ID_NAME = 2;
        final int TICKETS_TABLE_ID_SEAT = 3;
        final int TICKETS_TABLE_ID_CATEGORY= 4;
        final int TICKETS_TABLE_ID_DATE= 5;
        final int TICKETS_TABLE_ID_PREMIERE= 6;
        final int TICKETS_TABLE_ID_PRICE= 7;
        final int TICKETS_TABLE_ID_IS_PAID= 8;
        Connection cn = null;
        List<TicketPurchased> purchasedTickets = new ArrayList<>();
        Statement st = null;
        ResultSet rsTickets = null;
        try {
            cn = FactoryConnection.getConnectionFromFactory();

            st = cn.createStatement();
            String queryTickets= "select * from jeeproject.tickets WHERE jeeproject.tickets.date = '" + date + "';";
            rsTickets = st.executeQuery(queryTickets);
            synchronized (DBTicketImpl.class) {
                while (rsTickets.next()) {
                    purchasedTickets.add(
                            new TicketPurchased(
                                    rsTickets.getInt(TICKETS_TABLE_ID_ID_TICKET),
                                    rsTickets.getString(TICKETS_TABLE_ID_NAME),
                                    rsTickets.getInt(TICKETS_TABLE_ID_SEAT),
                                    rsTickets.getString(TICKETS_TABLE_ID_CATEGORY),
                                    rsTickets.getDate(TICKETS_TABLE_ID_DATE),
                                    rsTickets.getString(TICKETS_TABLE_ID_PREMIERE),
                                    rsTickets.getInt(TICKETS_TABLE_ID_PRICE),
                                    rsTickets.getString(TICKETS_TABLE_ID_IS_PAID)
                                    )
                    );
                }
            }


        } catch(SQLException | NamingException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            ConnectionService.closeService(cn,st,rsTickets);
        }

        return purchasedTickets;
    }
    @Override
    public void createRept(String date) throws DaoException, DataBaseConnectionException {
        Connection cn = null;
        List<TicketPurchased> purchasedTickets = new ArrayList<>();
        Statement st = null;
        ResultSet rsTickets = null;
        try {
            cn = FactoryConnection.getConnectionFromFactory();
            st = cn.createStatement();
            String queryTickets= "select * from jeeproject.tickets WHERE jeeproject.tickets.date = '" + date +
                    "' and ispaid='" + STATUS_NOT_PAID + "';";
            rsTickets = st.executeQuery(queryTickets);
            synchronized (DBTicketImpl.class) {
                while (rsTickets.next()) {
                    purchasedTickets.add(
                            new TicketPurchased(
                                    rsTickets.getInt(1),
                                    rsTickets.getString(2),
                                    rsTickets.getInt(3),
                                    rsTickets.getString(4),
                                    rsTickets.getDate(5),
                                    rsTickets.getString(6),
                                    rsTickets.getInt(7),
                                    rsTickets.getString(8)
                            )
                    );
                }
            }


        } catch(SQLException | NamingException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            ConnectionService.closeService(cn,st,rsTickets);
        }
        writeInFile(purchasedTickets, date);
    }
    private void writeInFile(List<TicketPurchased> list, String date) throws DaoException {
        int i = 1;
        Writer writer = null;
        try {
            writer = new FileWriter(PATH_FOR_REPT + date + ".txt");

            writer.write("Unpaid tickets in " + date + "\n");
            for (TicketPurchased line : list) {
                writer.write(i++ + ". ");
                writer.write("idTicket - " + line.getId() + ";client - " + line.getName() +
                        ";seat - " + line.getNumber() + "(" + line.getCategory() + ");\n");
            }
            writer.flush();
        } catch (IOException e) {
            throw new DaoException(e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new DaoException(ex.getMessage());
                }
            }
        }
    }
}
