package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    private static final String DRIVER_CLASS
            = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        Class.forName(DRIVER_CLASS);

        String url
                = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=cvdtt1;"
                + "integratedSecurity=true;"
                + "encrypt=false;"
                + "trustServerCertificate=true;";

        return DriverManager.getConnection(url);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {

            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Kết nối SQL Server thành công!");
                System.out.println("Database: " + conn.getCatalog());
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Không tìm thấy JDBC Driver!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Kết nối SQL Server thất bại!");
            e.printStackTrace();
        }
    }
}
