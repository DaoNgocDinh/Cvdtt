package repository;

import database.DatabaseConnection;
import model.risk.RuiRo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RiskRepository {

    public List<RuiRo> getAllRuiRo() {
        List<RuiRo> list = new ArrayList<>();
        String sql = "SELECT MaRuiRo, TenRuiRo, LoaiRuiRo, ChiTiet, NgayPhatHien, MaKhoanVay "
                   + "FROM RuiRo ORDER BY NgayPhatHien DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RuiRo r = new RuiRo();
                r.setMaRuiRo(rs.getString("MaRuiRo"));
                r.setTenRuiRo(rs.getString("TenRuiRo"));
                r.setLoaiRuiRo(rs.getString("LoaiRuiRo"));
                r.setChiTiet(rs.getString("ChiTiet"));
                r.setNgayPhatHien(rs.getDate("NgayPhatHien").toLocalDate());
                r.setMaKhoanVay(rs.getString("MaKhoanVay"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[RiskRepository] Lỗi khi lấy dữ liệu rủi ro: " + e.getMessage());
        }
        return list;
    }

    public RuiRo getRuiRoByMa(String maRuiRo) {
        String sql = "SELECT * FROM RuiRo WHERE MaRuiRo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maRuiRo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                RuiRo r = new RuiRo();
                r.setMaRuiRo(rs.getString("MaRuiRo"));
                r.setTenRuiRo(rs.getString("TenRuiRo"));
                r.setLoaiRuiRo(rs.getString("LoaiRuiRo"));
                r.setChiTiet(rs.getString("ChiTiet"));
                r.setNgayPhatHien(rs.getDate("NgayPhatHien").toLocalDate());
                r.setMaKhoanVay(rs.getString("MaKhoanVay"));
                return r;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}