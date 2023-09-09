package Payment;

import Stripe.Card;
import Tools.DB_Util;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Payment {
    private int paymentId;
    private int bookingId;
    private double paymentAmount;
    private String currency;
    private String paymentMethod;
    private String paymentDate;
    private String paymentTime;
    private String paymentStatus;

    public Payment() {
    }

    public Payment(int paymentId, int bookingId, double paymentAmount, String currency, String paymentMethod, String paymentDate, String paymentTime, String paymentStatus) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.paymentAmount = paymentAmount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public abstract boolean pay(double amount, Card card);

    protected void insertPayment() {
        String sql = "INSERT INTO PAYMENT (BOOKING_ID, PAYMENT_METHOD, PAYMENT_AMOUNT, CURRENCY, PAYMENT_DATE, PAYMENT_TIME, PAYMENT_STATUS) VALUES (?,?,?,?,?,?,?)";

        Connection conn = DB_Util.getConnection();

        try {
            int insert = DB_Util.executeUpdate(conn, sql, bookingId, paymentMethod, paymentAmount, currency, paymentDate, paymentTime, paymentStatus);

            if(insert == 1) {
                System.out.println("Insert payment successfully.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
