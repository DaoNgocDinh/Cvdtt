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
    private static final String SERVER = "localhost";
    private static final String DATABASE = "cvdtt1";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "YourStrong!Passw0rd";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS);
        String url = String.format("jdbc:sqlserver://%s;databaseName=%s;encrypt=false;trustServerCertificate=true", SERVER, DATABASE);
        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }
}
