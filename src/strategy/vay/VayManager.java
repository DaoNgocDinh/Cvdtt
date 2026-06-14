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

        strategy.xuLyKhoanVay(vay);
        luuVaoDatabase(vay);

        SecurityService.getInstance().accessFeature(
                vay.getMaTaiKhoan(),
                "Tao khoan vay " + vay.getMaKhoanVay()
        );
    }

    private void luuVaoDatabase(Vay vay) {
        String sql = "INSERT INTO Vay(MaKhoanVay, MaTaiKhoan, NgayVay, SoTienVay, HanTraNo) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vay.getMaKhoanVay());
            ps.setString(2, vay.getMaTaiKhoan());
            ps.setDate(3, Date.valueOf(vay.getNgayVay()));
            ps.setBigDecimal(4, vay.getSoTienVay());
            ps.setDate(5, Date.valueOf(vay.getHanTraNo()));

            ps.executeUpdate();

            System.out.println("[STRATEGY-VAY] Saved to table Vay successfully.");
            System.out.println("[STRATEGY-VAY] MaKhoanVay: " + vay.getMaKhoanVay());
            System.out.println("[STRATEGY-VAY] MaTaiKhoan: " + vay.getMaTaiKhoan());

        } catch (Exception e) {
            System.out.println("[STRATEGY-VAY] Save error: " + e.getMessage());
        }
    }
}