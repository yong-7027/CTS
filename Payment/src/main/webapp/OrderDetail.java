package src.main.webapp;

public class OrderDetail {
    private String movieName;
    private float price;
    private int quantity;
    private float subtotal;
    private float fee;
    private float total;

    public OrderDetail() {
    }

    public OrderDetail(String movieName, String price, String quantity, String fee) {
        this.movieName = movieName;
        this.price = Float.parseFloat(price);
        this.quantity = Integer.parseInt(quantity);
        this.fee = Float.parseFloat(fee);
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    public String getPrice() {
        return String.format("%.2f", price);
    }

    public void setPrice(String price) {
        this.fee = Float.parseFloat(price);
    }

    public String getQuantity() {
        return String.format("%d", quantity);
    }

    public void setQuantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }

    public String getFee() {
        return String.format("%.2f", fee);
    }

    public void setFee(String fee) {
        this.fee = Float.parseFloat(fee);
    }

    public String getSubTotal(){
        subtotal = price * (float) quantity;
        return String.format("%.2f", subtotal);
    }

    public String getTotal() {
        total = subtotal + fee;
        return String.format("%.2f", total);
    }

}

