/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author FPTSHOP
 */
public class DatabaseConnection {

    private static final SQLServerConnection SERVER_CONNECTION = new SQLServerConnection();

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return SERVER_CONNECTION.getConnection();
    }
}
