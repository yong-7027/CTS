package Tools;

import java.sql.*;

//public class DB_Util {
//    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cinema";
//    private static final String DB_UNAME = "root";
//    private static final String DB_PSD = "qwerty";
//
//    // 不能创建对象
//    private DB_Util() {
//    }
//
//    public static Connection getConnection() throws SQLException {
//        // 获取连接
//        return DriverManager.getConnection(DB_URL, DB_UNAME, DB_PSD);
//    }
//
//    public static ResultSet selectQueryById(String selectedThing, String tableName, String idColumn, Object... params) throws SQLException {
//        try {
//            Connection conn = getConnection();
//
//            String sql;
//            PreparedStatement stmt;
//
//            if (params == null) {
//                // Select statement without ID
//                sql = "SELECT " + selectedThing + " FROM " + tableName;
//                stmt = conn.prepareStatement(sql);
//            }
//            else {
//                // Select statement by ID
//                sql = sql = "SELECT " + selectedThing + " FROM " + tableName + " WHERE " + idColumn;
//                stmt = conn.prepareStatement(sql);
//                for (int i = 0; i < params.length; i++) {
//                    stmt.setObject(i + 1, params[i]);  // 预处理语句中的参数索引从 1 开始，所以在循环中使用 i + 1 来为每个参数设置对应的索引
//                }
//            }
//
//            return stmt.executeQuery();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            throw new SQLException("Error executing SELECT query: " + e.getMessage());
//        }
//    }
//
//    public static int insertQuery(String sql, Object... params) throws SQLException {
//        try {
//            Connection conn = getConnection();
//
//            PreparedStatement stmt = conn.prepareStatement(sql);
//
//            for (int i = 0; i < params.length; i++) {
//                stmt.setObject(i + 1, params[i]);  // 预处理语句中的参数索引从 1 开始，所以在循环中使用 i + 1 来为每个参数设置对应的索引
//            }
//            return stmt.executeUpdate();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            throw new SQLException("Error executing INSERT query: " + e.getMessage());
//        }
//    }
//
//    public static int updateQuery(String sql, Object... params) throws SQLException {
//        try {
//            Connection conn = getConnection();
//
//            PreparedStatement stmt = conn.prepareStatement(sql);
//
//            for (int i = 0; i < params.length; i++) {
//                stmt.setObject(i + 1, params[i]);  // 预处理语句中的参数索引从 1 开始，所以在循环中使用 i + 1 来为每个参数设置对应的索引
//            }
//            return stmt.executeUpdate();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            throw new SQLException("Error executing UPDATE query: " + e.getMessage());
//        }
//    }
//
//    public static int deleteQueryById(String tableName, String statusColumnName, String idColumn, Object... params) throws SQLException {
//        try {
//            Connection conn = getConnection();
//
//            String sql = "UPDATE " + tableName + " SET " + statusColumnName + " = 0 WHERE " + idColumn + " = ?";
//
//            PreparedStatement stmt = conn.prepareStatement(sql);
//
//            for (int i = 0; i < params.length; i++) {
//                stmt.setObject(i + 1, params[i]);  // 预处理语句中的参数索引从 1 开始，所以在循环中使用 i + 1 来为每个参数设置对应的索引
//            }
//            return stmt.executeUpdate();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            throw new SQLException("Error executing DELETE query: " + e.getMessage());
//        }
//    }
//}

public class DB_Util {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cts";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "{M8246+qpzmS}";
    private static Connection connection;

    // init load driver
    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        DB_Util.loadDriver();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

//    public static void executeQuery(Connection conn, String sql, Object... params) {
//        try {
//            PreparedStatement stmt = conn.prepareStatement(sql);
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static ResultSet executeSelect(Connection connection, String sql) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);

        return stmt.executeQuery();
    }

    public static ResultSet executeSelect(Connection connection, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        return stmt.executeQuery();
    }

    public static int executeUpdate(Connection connection, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        return stmt.executeUpdate();
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

