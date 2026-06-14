/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import database.DatabaseConnection;
import model.account.TaiKhoan;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author FPTSHOP
 */
public class TaiKhoanRepository {

    private static final String FIND_BY_USERNAME_AND_PASSWORD_SQL = "SELECT t.MaTaiKhoan, t.HoTen, t.Email, t.MatKhau, t.ChucVu, t.Locker, t.SoDienThoai, t.CCCD, t.SoTienConNo, r.RoleName " +
            "FROM TaiKhoan t LEFT JOIN Role r ON t.MaTaiKhoan = r.MaTaiKhoan " +
            "WHERE t.MaTaiKhoan = ? AND t.MatKhau = ?";

    public TaiKhoan findByUsernameAndPassword(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME_AND_PASSWORD_SQL)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToTaiKhoan(resultSet);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TaiKhoan mapToTaiKhoan(ResultSet resultSet) throws SQLException {
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setMaTaiKhoan(resultSet.getString("MaTaiKhoan"));
        taiKhoan.setHoTen(resultSet.getString("HoTen"));
        taiKhoan.setEmail(resultSet.getString("Email"));
        taiKhoan.setMatKhau(resultSet.getString("MatKhau"));
        taiKhoan.setChucVu(resultSet.getString("ChucVu"));
        taiKhoan.setLocker(resultSet.getBoolean("Locker"));
        taiKhoan.setSoDienThoai(resultSet.getString("SoDienThoai"));
        taiKhoan.setCccd(resultSet.getString("CCCD"));
        taiKhoan.setSoTienConNo(resultSet.getBigDecimal("SoTienConNo"));
        taiKhoan.setRoleName(resultSet.getString("RoleName"));
        return taiKhoan;
    }
}
