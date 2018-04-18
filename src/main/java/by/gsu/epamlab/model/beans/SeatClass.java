package by.gsu.epamlab.model.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alex on 13.06.2016.
 */
public class SeatClass {
    private String category;
    private int price;
    private int amount;
    private int[] numberOfSeats;

    public SeatClass() {}

    public SeatClass(String category, int price, int amount) {
        this.category = category;
        this.price = price;
        this.amount = amount;
        setNumberOfSeats(amount);

    }
    public SeatClass(String category, String price, String amount){
        this.category = category;
        setPrice(price);
        setAmount(amount);
        setNumberOfSeats(Integer.parseInt(amount));
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
    public void setPrice(String price){
        try {
            this.price = Integer.parseInt(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public void setAmount(String amount){
        try {
            this.amount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public int[] getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int amount) {
       int[] d = new int[amount];
        for(int i = 0;i < d.length;i++ ){
            d[i] = i + 1;
        }
        numberOfSeats = d;
    }
    public void deleteSeat(int numberSeat){
        numberOfSeats[numberSeat - 1] += 1000;
    }


    @Override
    public String toString() {
        return "SeatClass{" +
                "category='" + category + '\'' +
                '}';
    }
}
