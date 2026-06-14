package repository;

import database.DatabaseConnection;
import model.loan.Vay;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VayRepository {

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
            System.out.println("[VayRepository] Lưu khoản vay thành công: " + vay.getMaKhoanVay());
        } catch (Exception e) {
            System.out.println("[VayRepository] Lỗi lưu khoản vay: " + e.getMessage());
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