package Discount;

import Tools.DB_Util;
import Tools.DateTime;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustPromoCode {
    public void getPromoCode(int userId) {
        ArrayList<Promotion> promotions = new ArrayList<>();
        String currentDate = DateTime.getCurrentDate();

        System.out.println("These are the promotion you can get now: ");

        String sql = "SELECT * " +
                     "FROM PROMOTION P " +
                     "WHERE ? BETWEEN START_DATE AND VALID_UNTIL " +
                     "AND USAGE_LIMIT > ? " +
                     "AND P.PROMOTION_ID NOT IN ( " +
                        "SELECT PC.PROMOTION_ID " +
                        "FROM PROMOTIONCODE PC " +
                        "WHERE PC.USER_ID = ?)";

        Connection conn = DB_Util.getConnection();
        ResultSet rs;

        {
            try {
                rs = DB_Util.executeSelect(conn, sql, currentDate, 0, userId);

                while(rs.next()) {
                    Promotion promotion = new Promotion();
                    promotion.setPromotionId(rs.getInt("PROMOTION_ID"));
                    promotion.setDescription(rs.getString("DESCRIPTION"));
                    promotion.setDiscountValue(rs.getDouble("DISCOUNT_VALUE"));
                    promotion.setStartDate(rs.getString("START_DATE"));
                    promotion.setValidUntil(rs.getString("VALID_UNTIL"));
                    promotion.setUsageLimit(rs.getInt("USAGE_LIMIT"));

                    promotions.add(promotion);
                }

                for(Promotion details: promotions) {
                    System.out.println(details);
                }

                // Create scanner object
                Scanner input = new Scanner(System.in);

                System.out.print("\nEnter promotion ID to get the promotion code: ");
                int promotionId = input.nextInt();

                for(Promotion details: promotions){
                    if(promotionId == details.getPromotionId()){
                        String newCode;
                        String codeStatus = "VALID";
                        ArrayList<String> codes;

                        GeneratePromotionCode promoCode = new GeneratePromotionCode();

                        codes = promoCode.loadPromotionCode();

                        if(codes == null) {
                            newCode = GeneratePromotionCode.generatePromotionCode(15);
                            System.out.println(newCode);
                        }

                        else {
                            newCode = GeneratePromotionCode.generatePromotionCode(15);
                            promoCode.checkPromoCode(newCode, codes);
                        }

                        insertPromoCode(userId, promotionId, newCode, codeStatus);
                        updateUsageLimit(promotionId);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Close database connection
        DB_Util.closeConnection();
    }

    public void insertPromoCode(int userId, int promotionId, String code, String codeStatus) {
        String sql = "INSERT INTO PROMOTIONCODE (USER_ID, PROMOTION_ID, CODE, CODE_STATUS) VALUES (?, ?, ?, ?)";

        // Connect to database
        Connection conn = DB_Util.getConnection();

        int insert;

        {
            try {
                // Try to insert new promotion code claimed by user
                insert = DB_Util.executeUpdate(conn, sql, userId, promotionId, code, codeStatus);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(insert == 1) {
            // Successfully insert new promotion code
            System.out.println("Create successfully!");
        }
    }

    public void updateUsageLimit(int promotionId) {
        // To know current promotion remain claim times
        String selectSql = "SELECT USAGE_LIMIT FROM PROMOTION WHERE PROMOTION_ID = ?";
        int usageLimit = 0;

        // Connect Database
        Connection conn = DB_Util.getConnection();

        ResultSet rs;

        {
            try {
                rs = DB_Util.executeSelect(conn, selectSql, promotionId);

                while(rs.next()){
                    usageLimit = rs.getInt("USAGE_LIMIT");
                }

                // Substract 1 time
                usageLimit--;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Update new usage limit
        String updateSql = "UPDATE PROMOTION SET USAGE_LIMIT = ? WHERE PROMOTION_ID = ?";

        int update;

        {
            try {
                update = DB_Util.executeUpdate(conn, updateSql, usageLimit, promotionId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                // 在finally块中关闭连接，确保连接被关闭
                DB_Util.closeConnection();
            }
        }

        if(update == 1) {
            System.out.println("Update Usage Limit Successfully!");
        }
    }

    public double promoCodeVerification(int userId, String code) {
        double discountValue;
        String codeStatus = "VALID";

        String sql = "SELECT DISCOUNT_VALUE " +
                     "FROM PROMOTION P, PROMOTIONCODE PC " +
                     "WHERE P.PROMOTION_ID = PC.PROMOTION_ID " +
                     "AND USER_ID = ? " +
                     "AND CODE = ? " +
                     "AND CODE_STATUS = ?";

        Connection conn = DB_Util.getConnection();

        try {
            ResultSet rs = DB_Util.executeSelect(conn, sql, userId, code, codeStatus);

            while(rs.next()) {
                discountValue = rs.getDouble("DISCOUNT_VALUE");
                return discountValue;
            }

        } catch (SQLException e) {
            System.out.println("Database connection error.");

            throw new RuntimeException(e);

        }  finally {
            // 在finally块中关闭连接，确保连接被关闭
            DB_Util.closeConnection();
        }

        return 0;
    }

    public int updatePromoCodeStatus(int userId, String code) {
        String codeStatus = "USED";
        String sql = "UPDATE PROMOTIONCODE SET CODE_STATUS = ? WHERE USER_ID = ? AND CODE = ?";

        Connection conn = DB_Util.getConnection();

        try {
            int update = DB_Util.executeUpdate(conn, sql, codeStatus, userId, code);

            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
