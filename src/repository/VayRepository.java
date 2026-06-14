package repository;

import database.DatabaseConnection;
import model.loan.Vay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VayRepository {

    public List<Vay> findAll() {

        List<Vay> danhSach = new ArrayList<>();

        String sql =
                "SELECT * FROM Vay";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Vay vay = new Vay();

                vay.setMaKhoanVay(
                        rs.getString("MaKhoanVay"));

                vay.setMaTaiKhoan(
                        rs.getString("MaTaiKhoan"));

                vay.setNgayVay(
                        rs.getDate("NgayVay")
                                .toLocalDate());

                vay.setSoTienVay(
                        rs.getBigDecimal("SoTienVay"));

                vay.setHanTraNo(
                        rs.getDate("HanTraNo")
                                .toLocalDate());

                danhSach.add(vay);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return danhSach;
    }

    public boolean existsById(String maKhoanVay) {

        String sql = "SELECT 1 FROM Vay WHERE MaKhoanVay = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maKhoanVay);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Vay vay) {

        String sql =
                "INSERT INTO Vay " +
                        "(MaKhoanVay, MaTaiKhoan, NgayVay, SoTienVay, HanTraNo) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, vay.getMaKhoanVay());
            ps.setString(2, vay.getMaTaiKhoan());

            ps.setDate(
                    3,
                    java.sql.Date.valueOf(
                            vay.getNgayVay()));

            ps.setBigDecimal(
                    4,
                    vay.getSoTienVay());

            ps.setDate(
                    5,
                    java.sql.Date.valueOf(
                            vay.getHanTraNo()));

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
