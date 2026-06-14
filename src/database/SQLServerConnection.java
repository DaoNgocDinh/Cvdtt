/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FPTSHOP
 */
public class SQLServerConnection {

    private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Default values set to user's local instance (use host:port to avoid instance resolution); can be overridden with system properties
    private static final String SERVER = "AnhTuan";
    private static final String DATABASE = "cvdtt1";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";

    /**
     * Returns a JDBC connection. Behavior can be configured via system properties:
     * -db.server (default: localhost)
     * -db.database (default: cvdtt1)
     * -db.useIntegratedSecurity (true|false) when true uses Windows Integrated Authentication
     * -db.username -db.password used when integrated security is false
     *
     * Note: Windows Integrated Authentication requires the Microsoft sqljdbc_auth.dll
     * on the JVM library path (see driver distribution auth/x64 or auth/x86 folders).
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS);

        String server = System.getProperty("db.server", SERVER);
        String database = System.getProperty("db.database", DATABASE);
        boolean useIntegrated = Boolean.parseBoolean(System.getProperty("db.useIntegratedSecurity", "false"));

        String url = String.format("jdbc:sqlserver://%s;databaseName=%s;encrypt=false;trustServerCertificate=true", server, database);

        if (useIntegrated) {
            // Use Windows Integrated Authentication. Requires sqljdbc_auth.dll available to the JVM.
            url = url + ";integratedSecurity=true";
            return DriverManager.getConnection(url);
        } else {
            String username = System.getProperty("db.username", USERNAME);
            String password = System.getProperty("db.password", PASSWORD);
            return DriverManager.getConnection(url, username, password);
        }
    }
}
