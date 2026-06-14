package strategy.vay;

import database.DatabaseConnection;
import model.loan.Vay;
import singleton.SecurityService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class VayManager {
    
    private VayStrategy strategy;

    public void setStrategy(VayStrategy strategy) {
        this.strategy = strategy;
        System.out.println("[STRATEGY-VAY] Selected strategy: "
                + strategy.getClass().getSimpleName());
    }

    public void taoKhoanVay(Vay vay) {
        if (strategy == null) {
            System.out.println("[STRATEGY-VAY] Chua chon strategy.");
            return;
        }

        // Xử lý logic theo Strategy (tính hạn trả, lãi suất, tổng lãi, trả góp...)
        strategy.xuLyKhoanVay(vay);

        // In thông tin tính toán lãi vay
        System.out.println("[STRATEGY-VAY] Lai suat: " + vay.getLaiSuat());
        System.out.println("[STRATEGY-VAY] Tong lai phai tra: " + vay.getTongLaiPhaiTra());
        System.out.println("[STRATEGY-VAY] So tien tra hang thang: " + vay.getSoTienTraHangThang());

        // Lưu vào database
        luuVaoDatabase(vay);

        SecurityService.getInstance().accessFeature(
                vay.getMaTaiKhoan(),
                "Tao khoan vay " + vay.getMaKhoanVay()
        );
    }

    private void luuVaoDatabase(Vay vay) {
        // Cập nhật SQL để lưu thêm thông tin lãi vay
        String sql = "INSERT INTO Vay(MaKhoanVay, MaTaiKhoan, NgayVay, SoTienVay, HanTraNo, "
                   + "LaiSuat, TongLaiPhaiTra, SoTienTraHangThang) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vay.getMaKhoanVay());
            ps.setString(2, vay.getMaTaiKhoan());
            ps.setDate(3, Date.valueOf(vay.getNgayVay()));
            ps.setBigDecimal(4, vay.getSoTienVay());
            ps.setDate(5, Date.valueOf(vay.getHanTraNo()));
            
            // Các trường mới
            ps.setBigDecimal(6, vay.getLaiSuat());
            ps.setBigDecimal(7, vay.getTongLaiPhaiTra());
            ps.setBigDecimal(8, vay.getSoTienTraHangThang());

            ps.executeUpdate();
            
            System.out.println("[STRATEGY-VAY] Saved to table Vay successfully.");
            System.out.println("[STRATEGY-VAY] MaKhoanVay: " + vay.getMaKhoanVay());

        } catch (Exception e) {
            System.out.println("[STRATEGY-VAY] Save error: " + e.getMessage());
        }
    }
}