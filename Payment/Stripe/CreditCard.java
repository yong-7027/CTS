package Stripe;

public class CreditCard {
    private String cardNo;
    private String expiredDate;
    private String cvc;
    private String email;

    public CreditCard() {
    }

    public CreditCard(String cardNo, String expiredDate, String cvc, String email) {
        this.cardNo = cardNo;
        this.expiredDate = expiredDate;
        this.cvc = cvc;
        this.email = email;
    }

    public String getCardNo(){
        return cardNo;
    }

    public void setCardNo(String cardNo){
        this.cardNo = cardNo;
    }

    public String getExpiredDate(){
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate){
        this.expiredDate = expiredDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
