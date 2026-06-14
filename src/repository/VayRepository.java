package repository;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.loan.Vay;

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
        String sql = "INSERT INTO Vay(MaKhoanVay, MaTaiKhoan, NgayVay, SoTienVay, HanTraNo) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vay.getMaKhoanVay());
            ps.setString(2, vay.getMaTaiKhoan());
            ps.setDate(3, vay.getNgayVay() != null ? Date.valueOf(vay.getNgayVay()) : null);
            ps.setBigDecimal(4, vay.getSoTienVay());
            ps.setDate(5, vay.getHanTraNo() != null ? Date.valueOf(vay.getHanTraNo()) : null);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Vay> getAll() {
        List<Vay> list = new ArrayList<>();
        String sql = "SELECT MaKhoanVay, MaTaiKhoan, NgayVay, SoTienVay, HanTraNo "
                   + "FROM Vay ORDER BY NgayVay DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vay v = new Vay();
                v.setMaKhoanVay(rs.getString("MaKhoanVay"));
                v.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                v.setNgayVay(rs.getDate("NgayVay") != null ? rs.getDate("NgayVay").toLocalDate() : null);
                v.setSoTienVay(rs.getBigDecimal("SoTienVay"));
                v.setHanTraNo(rs.getDate("HanTraNo") != null ? rs.getDate("HanTraNo").toLocalDate() : null);
                list.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[VayRepository] Lỗi lấy danh sách vay: " + e.getMessage());
        }
        return list;
    }

    public Vay getById(String maKhoanVay) {
        String sql = "SELECT MaKhoanVay, MaTaiKhoan, NgayVay, SoTienVay, HanTraNo "
                   + "FROM Vay WHERE MaKhoanVay = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKhoanVay);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Vay v = new Vay();
                v.setMaKhoanVay(rs.getString("MaKhoanVay"));
                v.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                v.setNgayVay(rs.getDate("NgayVay") != null ? rs.getDate("NgayVay").toLocalDate() : null);
                v.setSoTienVay(rs.getBigDecimal("SoTienVay"));
                v.setHanTraNo(rs.getDate("HanTraNo") != null ? rs.getDate("HanTraNo").toLocalDate() : null);
                return v;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[VayRepository] Lỗi lấy chi tiết khoản vay: " + e.getMessage());
        }
        return null;
    }
}