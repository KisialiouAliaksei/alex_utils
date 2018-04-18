package by.gsu.epamlab.model.beans;

import java.sql.Date;

/**
 * Created by alex on 01.07.2016.
 */
public class TicketPurchased extends Ticket {
    private int id;
    private Status paid;

    private static enum Status{
        PAID,NOT_PAID,CANCELED;
    }

    public TicketPurchased(int id,String name, int number, String category, Date date, String premiere,int price, String isPaid) {
        super(name, number, category, date, premiere,price);
        this.id = id;
        this.paid = Status.valueOf(isPaid.toUpperCase());
    }

    public TicketPurchased(int id, String name, int number, String category, String date, String premiere,int price, String isPaid) {
        super(name, number, category, date, premiere,price);
        this.id = id;
        this.paid = Status.valueOf(isPaid.toUpperCase());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getPaid() {
        return paid;
    }

    public void setPaid(Status paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "idTicket:" + id + ";" + super.toString() + ";status:" + paid;
    }
}
