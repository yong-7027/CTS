package PaymentMethod;

import Tools.DB_Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultPaymentMethod {
    private int paymentMethodId;
    private int userId;
    private String paymentMethod;
    private String status;

    public DefaultPaymentMethod() {
    }

    public DefaultPaymentMethod(int userId, String paymentMethod, String status) {
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public DefaultPaymentMethod(int paymentMethodId, int userId, String paymentMethod, String status) {
        this.paymentMethodId = paymentMethodId;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public ArrayList<DefaultPaymentMethod> getAllDefaultPayments() {
//        String sql = "SELECT * FROM DEFAULT_PAYMENT_METHOD";
//
//        Connection conn = DB_Util.getConnection();
//
//        ResultSet rs;
//
//        try {
//            rs = DB_Util.executeSelect(conn, sql, (Object) null);
//
//            ArrayList<DefaultPaymentMethod> defaultPaymentMethods = new ArrayList<>();
//
//            while (rs.next()) {
//                paymentMethodId = rs.getInt("PAYMENT_METHOD_ID");
//                userId = rs.getInt("USER_ID");
//                paymentMethod = rs.getString("PAYMENT_METHOD");
//                status = rs.getString("STATUS");
//
//                DefaultPaymentMethod defaultPaymentMethod = new DefaultPaymentMethod(paymentMethodId, userId, paymentMethod, status);
//                defaultPaymentMethods.add(defaultPaymentMethod);
//            }
//
//            return defaultPaymentMethods;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String[] isDefaultPayment(ArrayList<DefaultPaymentMethod> defaultPaymentMethods, int userId, String status) {
        int i = 0;
        String[] defaultPayment = new String[2];

        for (DefaultPaymentMethod defaultPaymentMethod : defaultPaymentMethods) {
            if (userId == defaultPaymentMethod.userId && status.equals(defaultPaymentMethod.status)) {
                defaultPayment[i] = defaultPaymentMethod.getPaymentMethod();
                i++;
            }
        }

        if (i == 0) {
            // Not found any default payment method
            return null;
        } else {
            return defaultPayment;
        }
    }
//
//    public void insertDefaultPaymentMethod() {
//        String sql = "INSERT INTO DEFAULT_PAYMENT_METHOD (USER_ID, PAYMENT_METHOD, STATUS) VALUES (?, ?, ?)";
//
//        Connection conn = DB_Util.getConnection();
//
//        try {
//            int insert = DB_Util.executeUpdate(conn, sql, userId, paymentMethod, status);
//
//            if (insert == 1) {
//                System.out.println("Successfully set your default payment method. It will be applied at the next payment.");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void upDateDefaultPaymentMethod(int userId, String paymentMethod, String status) {
//        String sql = "UPDATE DEFAULT_PAYMENT_METHOD SET STATUS = ? WHERE USER_ID = ? AND PAYMENT_METHOD = ?";
//
//        Connection conn = DB_Util.getConnection();
//
//        try {
//            int update = DB_Util.executeUpdate(conn, sql, status, userId, paymentMethod);
//
//            if (update == 1) {
//                System.out.println("Successfully set your default payment method. It will be applied at the next payment.");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void delDefaultPaymentMethod(int userId, String paymentMethod) {
//        String status = "DELETED";
//
//        String sql = "UPDATE DEFAULT_PAYMENT_METHOD SET STATUS = ? WHERE USER_ID = ? AND PAYMENT_METHOD = ?";
//
//        Connection conn = DB_Util.getConnection();
//
//        try {
//            int delete = DB_Util.executeUpdate(conn, sql, status, userId, paymentMethod);
//
//            if (delete == 1) {
//                System.out.println("Successfully delete default payment method.");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
