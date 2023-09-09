package Discount;

import Tools.DB_Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminPromoCode {
    Promotion promotion = new Promotion();
    public void createPromotion() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please fill in all the following required information: ");
        System.out.print("Description: ");
        String description = input.nextLine();

        System.out.print("Discount value: ");
        double discountValue = input.nextDouble();

        System.out.print("Start date: ");
        String startDate = input.next();

        System.out.print("Valid until: ");
        String validUntil = input.next();

        System.out.print("Usage Limit: ");
        int usageLimit = input.nextInt();

        promotion.setDescription(description);
        promotion.setDiscountValue(discountValue);
        promotion.setStartDate(startDate);
        promotion.setValidUntil(validUntil);
        promotion.setUsageLimit(usageLimit);
    }

    public void insertPromotion() {
        String sql = "INSERT INTO PROMOTION (DESCRIPTION, DISCOUNT_VALUE, START_DATE, VALID_UNTIL, USAGE_LIMIT) VALUES (?, ?, ?, ?, ?)";

        Connection conn = DB_Util.getConnection();
        int insert;

        {
            try {
                insert = DB_Util.executeUpdate(conn, sql, promotion.getDescription(), promotion.getDiscountValue(), promotion.getStartDate(), promotion.getValidUntil(), promotion.getUsageLimit());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(insert == 1) {
            System.out.println("Create successfully!");
        }
    }
}
