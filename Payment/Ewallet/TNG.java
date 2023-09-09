package Ewallet;

public class TNG {
    private double amount;
    private String name;
    private String myKadNo;
    private String pinCode;

    public TNG() {
    }

    public TNG(double amount, String name, String myKadNo, String pinCode) {
        this.amount = amount;
        this.name = name;
        this.myKadNo = myKadNo;
        this.pinCode = pinCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyKadNo() {
        return myKadNo;
    }

    public void setMyKadNo(String myKadNo) {
        this.myKadNo = myKadNo;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
