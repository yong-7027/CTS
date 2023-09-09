package Order;

import java.util.ArrayList;

public class OrderDetails {
    private String movieName;
    private double price;

    public OrderDetails() {
    }

    public OrderDetails(String movieName, double price) {
        this.movieName = movieName;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
