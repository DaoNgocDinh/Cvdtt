package main;

import model.loan.Vay;
import model.risk.RuiRo;
import singleton.SecurityService;
import strategy.risk.RuiRoBaoMatStrategy;
import strategy.risk.RuiRoHoSoStrategy;
import strategy.risk.RuiRoManager;
import strategy.risk.RuiRoThanhToanStrategy;
import strategy.risk.RuiRoTinDungStrategy;
import strategy.vay.VayDaiHanStrategy;
import strategy.vay.VayManager;
import strategy.vay.VayNganHanStrategy;
import strategy.vay.VayTrungHanStrategy;
import java.math.BigDecimal;
import java.time.LocalDate;
import strategy.risk.RuiRoStrategy;

public class TestSingletonStrategyMain {
    
    public static void main(String[] args) {
        System.out.println("===== BAT DAU TEST SINGLETON + STRATEGY =====");

        // ==========================
        // TEST SINGLETON
        // ==========================
        System.out.println("\n===== TEST SINGLETON =====");
        SecurityService securityService = SecurityService.getInstance();
        
        boolean loginResult = securityService.login("AD001", "123456");
        if (!loginResult) {
            System.out.println("Khong dang nhap duoc. Dung test.");
            return;
        }
        
        securityService.accessFeature("AD001", "Test singleton va strategy");

        // ==========================
        // TEST STRATEGY VAY
        // ==========================
        System.out.println("\n===== TEST STRATEGY VAY =====");
        
        Vay vayNganHan = createVay("KV_TEST1", "KH001", new BigDecimal("10000000"));
        VayManager vayManager1 = new VayManager();
        vayManager1.setStrategy(new VayNganHanStrategy());
        vayManager1.taoKhoanVay(vayNganHan);

        Vay vayTrungHan = createVay("KV_TEST2", "KH002", new BigDecimal("50000000"));
        VayManager vayManager2 = new VayManager();
        vayManager2.setStrategy(new VayTrungHanStrategy());
        vayManager2.taoKhoanVay(vayTrungHan);

        Vay vayDaiHan = createVay("KV_TEST3", "KH003", new BigDecimal("300000000"));
        VayManager vayManager3 = new VayManager();
        vayManager3.setStrategy(new VayDaiHanStrategy());
        vayManager3.taoKhoanVay(vayDaiHan);

        // ==========================
        // TEST STRATEGY RISK
        // ==========================
        System.out.println("\n===== TEST STRATEGY RISK =====");

        testRuiRo("RR_TEST1", "KV_TEST1", "Khach hang co so tien con no cao", 
                  new RuiRoTinDungStrategy());

        testRuiRo("RR_TEST2", "KV_TEST2", "Khach hang cham thanh toan", 
                  new RuiRoThanhToanStrategy());

        testRuiRo("RR_TEST3", "KV_TEST3", "Ho so vay thieu giay to", 
                  new RuiRoHoSoStrategy());

        testRuiRo("RR_TEST4", "KV_TEST1", "Truy cap bat thuong vao ho so khoan vay", 
                  new RuiRoBaoMatStrategy());

        // ==========================
        // TEST LOG SINGLETON
        // ==========================
        System.out.println("\n===== TEST AUDIT LOG =====");
        securityService.logout("AD001");
        securityService.showAuditLogs();

        System.out.println("\n===== KET THUC TEST =====");
    }

    // Helper method để tạo Vay gọn hơn
    private static Vay createVay(String maKhoanVay, String maTaiKhoan, BigDecimal soTien) {
        Vay vay = new Vay();
        vay.setMaKhoanVay(maKhoanVay);
        vay.setMaTaiKhoan(maTaiKhoan);
        vay.setNgayVay(LocalDate.now());
        vay.setSoTienVay(soTien);
        vay.setHanTraNo(null);
        return vay;
    }

    // Helper method để test RuiRo gọn hơn
    private static void testRuiRo(String maRuiRo, String maKhoanVay, String chiTiet, 
                                  RuiRoStrategy strategy) {
        
        RuiRo ruiRo = new RuiRo();
        ruiRo.setMaRuiRo(maRuiRo);
        ruiRo.setMaKhoanVay(maKhoanVay);
        ruiRo.setChiTiet(chiTiet);
        ruiRo.setNgayPhatHien(LocalDate.now());

        RuiRoManager manager = new RuiRoManager();
        manager.setStrategy(strategy);
        manager.danhGiaRuiRo(ruiRo);   // ← Đã sửa thành danhGiaRuiRo

        System.out.println(">>> Muc do rui ro: " + strategy.getMucDoRuiRo());
        System.out.println("--------------------------------------------------");
    }
}