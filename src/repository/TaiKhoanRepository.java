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

    private static final String FIND_BY_USERNAME_AND_PASSWORD_SQL = "SELECT t.MaTaiKhoan, t.HoTen, t.Email, t.MatKhau, t.ChucVu, t.Locker, t.SoDienThoai, t.CCCD, t.SoTienConNo, r.RoleName "
            + "FROM TaiKhoan t LEFT JOIN Role r ON t.MaTaiKhoan = r.MaTaiKhoan "
            + "WHERE t.MaTaiKhoan = ? AND t.MatKhau = ?";

    public TaiKhoan findByUsernameAndPassword(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME_AND_PASSWORD_SQL)) {
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

    public void save(TaiKhoan taiKhoan) {

        String taiKhoanSql
                = "INSERT INTO TaiKhoan "
                + "(MaTaiKhoan, HoTen, Email, MatKhau, ChucVu, Locker, SoDienThoai, CCCD, SoTienConNo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String roleSql
                = "INSERT INTO Role "
                + "(MaTaiKhoan, RoleName) "
                + "VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {

                PreparedStatement tkStmt
                        = connection.prepareStatement(taiKhoanSql);

                tkStmt.setString(1, taiKhoan.getMaTaiKhoan());
                tkStmt.setString(2, taiKhoan.getHoTen());
                tkStmt.setString(3, taiKhoan.getEmail());
                tkStmt.setString(4, taiKhoan.getMatKhau());
                tkStmt.setString(5, taiKhoan.getChucVu());
                tkStmt.setBoolean(6, taiKhoan.getLocker());

                tkStmt.setString(7, taiKhoan.getSoDienThoai());
                tkStmt.setString(8, taiKhoan.getCccd());
                tkStmt.setBigDecimal(9, taiKhoan.getSoTienConNo());

                tkStmt.executeUpdate();

                PreparedStatement roleStmt
                        = connection.prepareStatement(roleSql);

                roleStmt.setString(1, taiKhoan.getMaTaiKhoan());
                roleStmt.setString(2, taiKhoan.getRoleName());

                roleStmt.executeUpdate();

                connection.commit();

            } catch (Exception ex) {

                connection.rollback();
                throw ex;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void delete(String maTaiKhoan) {

        String deleteRoleSql
                = "DELETE FROM Role WHERE MaTaiKhoan = ?";

        String deleteTaiKhoanSql
                = "DELETE FROM TaiKhoan WHERE MaTaiKhoan = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {

                // Xóa role trước
                PreparedStatement roleStmt
                        = connection.prepareStatement(deleteRoleSql);

                roleStmt.setString(1, maTaiKhoan);
                roleStmt.executeUpdate();

                // Xóa tài khoản
                PreparedStatement tkStmt
                        = connection.prepareStatement(deleteTaiKhoanSql);

                tkStmt.setString(1, maTaiKhoan);
                tkStmt.executeUpdate();

                connection.commit();

            } catch (Exception ex) {

                connection.rollback();
                throw ex;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void update(TaiKhoan taiKhoan) {

        String sql
                = "UPDATE TaiKhoan "
                + "SET HoTen = ?, "
                + "Email = ?, "
                + "MatKhau = ?, "
                + "ChucVu = ?, "
                + "Locker = ?, "
                + "SoDienThoai = ?, "
                + "CCCD = ?, "
                + "SoTienConNo = ? "
                + "WHERE MaTaiKhoan = ?";

        try (Connection connection
                = DatabaseConnection.getConnection(); PreparedStatement stmt
                = connection.prepareStatement(sql)) {

            stmt.setString(1, taiKhoan.getHoTen());
            stmt.setString(2, taiKhoan.getEmail());
            stmt.setString(3, taiKhoan.getMatKhau());
            stmt.setString(4, taiKhoan.getChucVu());
            stmt.setBoolean(5, taiKhoan.getLocker());
            stmt.setString(6, taiKhoan.getSoDienThoai());
            stmt.setString(7, taiKhoan.getCccd());
            stmt.setBigDecimal(8, taiKhoan.getSoTienConNo());

            stmt.setString(9, taiKhoan.getMaTaiKhoan());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public TaiKhoan findByUsername(String username) {

        String sql
                = "SELECT t.MaTaiKhoan, t.HoTen, t.Email, t.MatKhau, t.ChucVu, t.Locker, "
                + "t.SoDienThoai, t.CCCD, t.SoTienConNo, r.RoleName "
                + "FROM TaiKhoan t "
                + "LEFT JOIN Role r ON t.MaTaiKhoan = r.MaTaiKhoan "
                + "WHERE t.MaTaiKhoan = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

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

    public void updateLockStatus(String maTaiKhoan, boolean locker) {

        String sql = "UPDATE TaiKhoan SET Locker = ? WHERE MaTaiKhoan = ?";

        try (Connection connection
                = DatabaseConnection.getConnection(); PreparedStatement stmt
                = connection.prepareStatement(sql)) {

            stmt.setBoolean(1, locker);
            stmt.setString(2, maTaiKhoan);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
