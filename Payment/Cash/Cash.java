package Cash;

public class Cash {
    private double amount;
    private double payAmount;
    private String currency;
    private String paymentDate;
    private String paymentTime;
    private double giveChange;

    public Cash() {
    }

    public Cash(double amount, double payAmount, String currency, String paymentDate, String paymentTime, double giveChange) {
        this.amount = amount;
        this.payAmount = payAmount;
        this.currency = currency;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.giveChange = giveChange;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public double getGiveChange() {
        return payAmount - amount;
    }
}
