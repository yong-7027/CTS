package PaymentMethod;

import Tools.DB_Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DefaultPaymentDAOImpl implements DefaultPaymentDAO {

    @Override
    public ArrayList<DefaultPaymentMethod> getAll() throws SQLException {
        String sql = "SELECT * FROM DEFAULT_PAYMENT_METHOD";

        Connection conn = DB_Util.getConnection();

        ResultSet rs;

        try {
            rs = DB_Util.executeSelect(conn, sql);

            ArrayList<DefaultPaymentMethod> defaultPaymentMethods = new ArrayList<>();

            while (rs.next()) {
                int paymentMethodId = rs.getInt("PAYMENT_METHOD_ID");
                int userId = rs.getInt("USER_ID");
                String paymentMethod = rs.getString("PAYMENT_METHOD");
                String status = rs.getString("STATUS");

                DefaultPaymentMethod defaultPaymentMethod = new DefaultPaymentMethod(paymentMethodId, userId, paymentMethod, status);
                defaultPaymentMethods.add(defaultPaymentMethod);
            }

            return defaultPaymentMethods;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(DefaultPaymentMethod defaultPaymentMethod) throws SQLException {
        String sql = "INSERT INTO DEFAULT_PAYMENT_METHOD (USER_ID, PAYMENT_METHOD, STATUS) VALUES (?, ?, ?)";

        Connection conn = DB_Util.getConnection();

        try {
            return DB_Util.executeUpdate(conn, sql, defaultPaymentMethod.getUserId(), defaultPaymentMethod.getPaymentMethod(), defaultPaymentMethod.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(DefaultPaymentMethod defaultPaymentMethod) throws SQLException {
        String sql = "UPDATE DEFAULT_PAYMENT_METHOD SET STATUS = ? WHERE USER_ID = ? AND PAYMENT_METHOD = ?";

        Connection conn = DB_Util.getConnection();

        try {
            return DB_Util.executeUpdate(conn, sql, defaultPaymentMethod.getStatus(), defaultPaymentMethod.getUserId(), defaultPaymentMethod.getPaymentMethod());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(DefaultPaymentMethod defaultPaymentMethod) {
        String sql = "UPDATE DEFAULT_PAYMENT_METHOD SET STATUS = ? WHERE USER_ID = ? AND PAYMENT_METHOD = ?";

        Connection conn = DB_Util.getConnection();

        try {
            return DB_Util.executeUpdate(conn, sql, defaultPaymentMethod.getStatus(), defaultPaymentMethod.getUserId(), defaultPaymentMethod.getPaymentMethod());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
