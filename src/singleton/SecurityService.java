package singleton;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SecurityService {

    private static SecurityService instance;

    private AuditLogManager auditLogManager;

    private SecurityService() {
        System.out.println("[SINGLETON] SecurityService constructor called.");
        auditLogManager = AuditLogManager.getInstance();
    }

    public static SecurityService getInstance() {
        System.out.println("[SINGLETON] SecurityService.getInstance() called.");

        if (instance == null) {
            System.out.println("[SINGLETON] Creating only one SecurityService instance.");
            instance = new SecurityService();
        } else {
            System.out.println("[SINGLETON] Reusing existing SecurityService instance.");
        }

        return instance;
    }

    public boolean login(String maTaiKhoan, String matKhau) {
        System.out.println("[SINGLETON] SecurityService.login() is running.");
        System.out.println("[SINGLETON] Checking TaiKhoan table...");

        String sql = "SELECT MaTaiKhoan, HoTen, Locker "
                + "FROM TaiKhoan "
                + "WHERE MaTaiKhoan = ? AND MatKhau = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maTaiKhoan);
            ps.setString(2, matKhau);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("[SINGLETON] Login failed: wrong MaTaiKhoan or MatKhau.");
                auditLogManager.writeLog(maTaiKhoan, "Dang nhap that bai");
                return false;
            }

            boolean locker = rs.getBoolean("Locker");

            if (locker) {
                System.out.println("[SINGLETON] Login failed: account is locked.");
                auditLogManager.writeLog(maTaiKhoan, "Dang nhap that bai do tai khoan bi khoa");
                return false;
            }

            String hoTen = rs.getString("HoTen");

            System.out.println("[SINGLETON] Login success.");
            System.out.println("[SINGLETON] MaTaiKhoan: " + maTaiKhoan);
            System.out.println("[SINGLETON] HoTen: " + hoTen);

            auditLogManager.writeLog(maTaiKhoan, "Dang nhap thanh cong");

            return true;

        } catch (Exception e) {
            System.out.println("[SINGLETON] Login error: " + e.getMessage());
            auditLogManager.writeLog(maTaiKhoan, "Loi dang nhap: " + e.getMessage());
            return false;
        }
    }

    public void logout(String maTaiKhoan) {
        System.out.println("[SINGLETON] SecurityService.logout() is running.");
        System.out.println("[SINGLETON] Logout account: " + maTaiKhoan);

        auditLogManager.writeLog(maTaiKhoan, "Dang xuat");
    }

    public void accessFeature(String maTaiKhoan, String tenChucNang) {
        System.out.println("[SINGLETON] SecurityService.accessFeature() is running.");
        System.out.println("[SINGLETON] Account " + maTaiKhoan
                + " accessed feature: " + tenChucNang);

        auditLogManager.writeLog(
                maTaiKhoan,
                "Truy cap chuc nang: " + tenChucNang
        );
    }

    public void showAuditLogs() {
        auditLogManager.showLogs();
    }
}