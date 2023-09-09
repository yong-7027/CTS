package Discount;

public class PromotionCode {
    private int codeId;
    private int userId;
    private int promotionId;
    private String code;
    private String dateCreated;
    private String codeStatus;

    public PromotionCode() {
    }

    public PromotionCode(int userId, int promotionId, String code, String dateCreated, String codeStatus) {
        this.userId = userId;
        this.promotionId = promotionId;
        this.code = code;
        this.dateCreated = dateCreated;
        this.codeStatus = codeStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }
}
