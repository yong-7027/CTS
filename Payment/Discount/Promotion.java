package Discount;

public class Promotion {
    private int promotionId;
    private String description;
    private double discountValue;
    private String startDate;
    private String validUntil;
    private int usageLimit;

    public Promotion() {
    }

    public Promotion(int promotionId, String description, double discountValue, String startDate, String validUntil, int usageLimit) {
        this.promotionId = promotionId;
        this.description = description;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.validUntil = validUntil;
        this.usageLimit = usageLimit;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    @Override
    public String toString() {
        return  "\nPromotion ID = " + promotionId
                + "\nDescription = " + description
                + "\nDiscount Value = " + discountValue
                + "\nStart From = " + startDate
                + "\nValid Until = " + validUntil
                + "\nRemain = " + usageLimit;
    }
}
