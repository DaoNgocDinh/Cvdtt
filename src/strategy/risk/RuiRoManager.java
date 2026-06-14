package strategy.risk;

import database.DatabaseConnection;
import model.risk.RuiRo;
import observer.subjects.RiskMonitoringService;
import singleton.SecurityService;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RuiRoManager {
    
    private RuiRoStrategy strategy;

    public void setStrategy(RuiRoStrategy strategy) {
        this.strategy = strategy;
        System.out.println("[STRATEGY-RISK] Selected strategy: " 
                + strategy.getClass().getSimpleName());
    }

    public void danhGiaRuiRo(RuiRo ruiRo) {
        if (strategy == null) {
            System.out.println("[STRATEGY-RISK] Chua chon strategy.");
            return;
        }

        // Gọi Strategy để xử lý
        strategy.danhGiaRuiRo(ruiRo);

        // Lấy mức độ rủi ro từ Strategy
        String mucDo = strategy.getMucDoRuiRo();
        System.out.println("[STRATEGY-RISK] Muc do rui ro: " + mucDo);

        // Lưu vào database
        luuVaoDatabase(ruiRo, mucDo);

        SecurityService.getInstance().accessFeature(
                ruiRo.getMaKhoanVay() != null ? ruiRo.getMaKhoanVay() : "N/A",
                "Danh gia rui ro " + ruiRo.getMaRuiRo()
        );
    }

    private void luuVaoDatabase(RuiRo ruiRo, String mucDoRuiRo) {
        String sql = "INSERT INTO RuiRo(MaRuiRo, MaKhoanVay, TenRuiRo, LoaiRuiRo, ChiTiet, NgayPhatHien) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ruiRo.getMaRuiRo());
            ps.setString(2, ruiRo.getMaKhoanVay());
            ps.setString(3, ruiRo.getTenRuiRo());
            ps.setString(4, ruiRo.getLoaiRuiRo());
            
            // Ghi kèm mức độ rủi ro vào ChiTiet vì không muốn thêm cột mới
            String chiTietCuoi = ruiRo.getChiTiet() 
                    + " | Muc do rui ro: " + mucDoRuiRo;
            ps.setString(5, chiTietCuoi);
            
            ps.setDate(6, java.sql.Date.valueOf(ruiRo.getNgayPhatHien()));

            ps.executeUpdate();
            System.out.println("[STRATEGY-RISK] Saved to table RuiRo successfully.");
            RiskMonitoringService.getInstance().publishRisk(ruiRo);

        } catch (Exception e) {
            System.out.println("[STRATEGY-RISK] Save error: " + e.getMessage());
        }
    }
}