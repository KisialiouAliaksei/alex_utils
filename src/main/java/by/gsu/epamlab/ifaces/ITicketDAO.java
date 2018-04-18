package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.model.beans.*;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;

import java.util.List;

/**
 * Created by alex on 17.06.2016.
 */
public interface ITicketDAO {
    boolean isTicketBuy(int seat, String date, Premiere premiere, String category) throws DataBaseConnectionException, DaoException;

    Seance showAviableTickets(String date, String prem, List<SeatClass> seats) throws DaoException, DataBaseConnectionException;

    List<TicketPurchased> getTickets(String date) throws DataBaseConnectionException, DaoException;

    List<Ticket> addTicket(String[] ticketList) throws DaoException, DataBaseConnectionException;

    boolean setPurchasedTicked(String[] id) throws DataBaseConnectionException, DaoException;

    void createRept(String date) throws DaoException, DataBaseConnectionException;

}
