package Stripe;

import Tools.DB_Util;
import Tools.DateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public void insert() {

        // Connect to database
        Connection conn = DB_Util.getConnection();

        // SQL
        String sql = "INSERT INTO payment VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the values
            stmt.setString(1, "P00005");
            stmt.setString(2, "E-WALLET");
            stmt.setString(3, DateTime.getCurrentDate());
            stmt.setString(4, DateTime.getCurrentTime());
            stmt.setDouble(5, 16.50);
            stmt.setString(6, "MYR");
            stmt.setString(7, "PAID");

            // Execute stmt
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close database connection
        DB_Util.closeConnection();
    }
}
