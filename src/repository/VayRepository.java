package repository;

import database.DatabaseConnection;
import model.loan.Vay;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class VayRepository {
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
