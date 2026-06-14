package strategy.risk;

import database.DatabaseConnection;
import model.risk.RuiRo;
import singleton.SecurityService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class RuiRoManager {

    private RuiRoStrategy strategy;

    public void setStrategy(RuiRoStrategy strategy) {
        this.strategy = strategy;
        System.out.println("[STRATEGY-RISK] Selected strategy: "
                + strategy.getClass().getSimpleName());
    }

    public void phatHienRuiRo(String maTaiKhoan, RuiRo ruiRo) {
        if (strategy == null) {
            System.out.println("[STRATEGY-RISK] Chua chon strategy.");
            return;
        }

        strategy.danhGiaRuiRo(ruiRo);
        luuVaoDatabase(ruiRo);

        SecurityService.getInstance().accessFeature(
                maTaiKhoan,
                "Phat hien rui ro " + ruiRo.getMaRuiRo()
        );
    }

    private void luuVaoDatabase(RuiRo ruiRo) {
        String sql = "INSERT INTO RuiRo(MaRuiRo, MaKhoanVay, TenRuiRo, LoaiRuiRo, ChiTiet, NgayPhatHien) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ruiRo.getMaRuiRo());
            ps.setString(2, ruiRo.getMaKhoanVay());
            ps.setString(3, ruiRo.getTenRuiRo());
            ps.setString(4, ruiRo.getLoaiRuiRo());
            ps.setString(5, ruiRo.getChiTiet());
            ps.setDate(6, Date.valueOf(ruiRo.getNgayPhatHien()));

            ps.executeUpdate();

            System.out.println("[STRATEGY-RISK] Saved to table RuiRo successfully.");
            System.out.println("[STRATEGY-RISK] MaRuiRo: " + ruiRo.getMaRuiRo());
            System.out.println("[STRATEGY-RISK] MaKhoanVay: " + ruiRo.getMaKhoanVay());

        } catch (Exception e) {
            System.out.println("[STRATEGY-RISK] Save error: " + e.getMessage());
        }
    }
}