package by.gsu.epamlab.model.beans;

import by.gsu.epamlab.model.exceptions.DaoException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by alex on 17.06.2016.
 */
public class Seance {
    private Date date;
    private Premiere premiere;
    private List<SeatClass> seats;
    private List<Ticket> tickets;
    public Seance() {
    }


    public Seance(Date date, Premiere premiere, List<SeatClass> seats, List<Ticket> purchasedTickets) {
        this.date = date;
        this.premiere = premiere;
        this.seats = seats;
        this.tickets = purchasedTickets;
    }
    public Seance(String date, String premiere, List<SeatClass> seats, List<Ticket> purchasedTickets) throws DaoException {
        setDates(date);
        setPremiere(premiere);
        this.seats = seats;
        this.tickets = purchasedTickets;
    }



    public Date getDate() {
        return date;
    }

    public void setDates(String date) throws DaoException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        long l = 0;
        try {
            l = format.parse(date).getTime();
        } catch (ParseException e) {
            throw new DaoException(e);
        }
        this.date = new Date(l);
    }

    public Premiere getPremiere() {
        return premiere;
    }

    public void setPremiere(Premiere premiere) {
        this.premiere = premiere;
    }
    public void setPremiere(String premiere) {
        this.premiere = new Premiere(premiere);
    }



    public List<SeatClass> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatClass> seats) {
        this.seats = seats;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    @Override
    public String toString() {
        return "Seance{" +
                "date=" + date +
                ", premiere=" + premiere +
                ", seats=" + seats +
                ", tickets=" + tickets +
                '}';
    }
}
