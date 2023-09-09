package Discount;

import Tools.DB_Util;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class GeneratePromotionCode {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new SecureRandom();

    // Generate random promotion code with specific length
    public static String generatePromotionCode(int length) {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }

    public ArrayList<String> loadPromotionCode() {
        String sql = "SELECT CODE FROM PROMOTIONCODE WHERE CODE_STATUS = ?";
        ArrayList<String> codes = new ArrayList<>();

        Connection conn = DB_Util.getConnection();
        ResultSet rs;

        {
            try {
                rs = DB_Util.executeSelect(conn, sql, "VALID");

                while (rs.next()) {
                    PromotionCode promotionCode = new PromotionCode();

                    codes.add(rs.getString("CODE"));

                    return codes;
//                    promotion.setPromotionId(rs.getInt("PROMOTION_ID"));
////                    promotion.setUserId(rs.getInt("USER_ID"));
////                    promotion.setCode(rs.getString("CODE"));
//                    promotion.setDescription(rs.getString("DESCRIPTION"));
//                    promotion.setDiscountValue(rs.getDouble("DISCOUNT_VALUE"));
////                    promotion.setDateCreated(rs.getString("DATE_CREATED"));
//                    promotion.setValidUntil(rs.getString("VALID_UNTIL"));
////                    promotion.setCodeStatus(rs.getString("CODE_STATUS"));
//
//                    promotions.add(promotion);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public void checkPromoCode(String newCode, ArrayList<String> codes) {
        int repeatCode;

        do {
            repeatCode = 0;

            for(String code: codes){
                if(newCode.equals(code)){
                    newCode = generatePromotionCode(15);
                    repeatCode++;
                    break;
                }
            }
        } while(repeatCode != 0);

        System.out.println("Created successfully!");
        System.out.println("Your code is: " + newCode);
    }
}


