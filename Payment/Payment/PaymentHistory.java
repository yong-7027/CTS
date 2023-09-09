package Payment;

import Tools.DB_Util;
import Tools.DateTime;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PaymentHistory {
    private int userId;
    private int searchRequire;
    private String startDate;
    private String endDate;

    public PaymentHistory() {
    }

    public PaymentHistory(int userId, int searchRequire, String startDate, String endDate) {
        this.userId = userId;
        this.searchRequire = searchRequire;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSearchRequire() {
        return searchRequire;
    }

    public void setSearchRequire(int searchRequire) {
        this.searchRequire = searchRequire;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void searchRequirement() {
        String sql = "SELECT * FROM PAYMENT WHERE USER_ID = ?";

        Connection conn = DB_Util.getConnection();

        ResultSet rs;

        try {
            if(searchRequire == 1) {
                // Last 30 days
                sql += " AND PAYMENT_DATE >= DATE_SUB(NOW(), INTERVAL ? DAY)";
                int searchLastDays = 30;

                rs = DB_Util.executeSelect(conn, sql, userId, searchLastDays);
            } else if (searchRequire == 2) {
                // All days
                sql += " AND PAYMENT_DATE >= DATE_SUB(NOW(), INTERVAL ? DAY)";
                rs = DB_Util.executeSelect(conn, sql, userId);
            } else {
                // Custom start date and end date
                sql += " AND PAYMENT_DATE BETWEEN ? AND ?";
                rs = DB_Util.executeSelect(conn, sql, userId, startDate, endDate);
            }

            while(rs.next()) {


                ArrayList<Payment> payments = new ArrayList<>();


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
