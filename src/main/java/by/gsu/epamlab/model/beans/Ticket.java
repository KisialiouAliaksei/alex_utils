package by.gsu.epamlab.model.beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by alex on 17.06.2016.
 */
public class Ticket {
    private int number;
    private String name;
    private String premiere;
    private Date date;
    private String category;
    private int price;

    public Ticket() {
    }

    public Ticket(String name,int number, String category, Date date, String premiere, int price) {
        this.number = number;
        this.name = name;
        this.premiere = premiere;
        this.date = date;
        this.category = category;
        this.price = price;
    }
    public Ticket(String name,String number, String category, String date, String premiere, String price) {
        setDate(date);
        setNumber(number);
        this.name = name;
        this.premiere = premiere;
        this.category = category;
        setPrice(price);
    }
    public Ticket(String name,int number, String category, String date, String premiere, int price) {
        setDate(date);
       this.number = number;
        this.name = name;
        this.premiere = premiere;
        this.category = category;
        this.price = price;
    }




    public void setNumber(int number) {
        this.number = number;
    }
    public void setNumber(String number){
        try {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setDate(String date){
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        long l = 0;
        try {
            l = format.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d = new Date(l);
        this.date = d;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setPrice(String price) {
        try {
            this.price = Integer.parseInt(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (number != ticket.number) return false;
        if (!name.equals(ticket.name)) return false;
        if (!premiere.equals(ticket.premiere)) return false;
        if (!date.equals(ticket.date)) return false;
        return category.equals(ticket.category);

    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + name.hashCode();
        result = 31 * result + premiere.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return ""+ name + ";" + number + ";" + category + ";" + date + ";" + premiere + ";" + price;
    }
}
