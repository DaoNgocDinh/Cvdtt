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

        Vay vayNganHan = new Vay();
        vayNganHan.setMaKhoanVay("KV_TEST1");
        vayNganHan.setMaTaiKhoan("KH001");
        vayNganHan.setNgayVay(LocalDate.now());
        vayNganHan.setSoTienVay(new BigDecimal("10000000"));
        vayNganHan.setHanTraNo(null);

        VayManager vayManager1 = new VayManager();
        vayManager1.setStrategy(new VayNganHanStrategy());
        vayManager1.taoKhoanVay(vayNganHan);


        Vay vayTrungHan = new Vay();
        vayTrungHan.setMaKhoanVay("KV_TEST2");
        vayTrungHan.setMaTaiKhoan("KH002");
        vayTrungHan.setNgayVay(LocalDate.now());
        vayTrungHan.setSoTienVay(new BigDecimal("50000000"));
        vayTrungHan.setHanTraNo(null);

        VayManager vayManager2 = new VayManager();
        vayManager2.setStrategy(new VayTrungHanStrategy());
        vayManager2.taoKhoanVay(vayTrungHan);


        Vay vayDaiHan = new Vay();
        vayDaiHan.setMaKhoanVay("KV_TEST3");
        vayDaiHan.setMaTaiKhoan("KH003");
        vayDaiHan.setNgayVay(LocalDate.now());
        vayDaiHan.setSoTienVay(new BigDecimal("300000000"));
        vayDaiHan.setHanTraNo(null);

        VayManager vayManager3 = new VayManager();
        vayManager3.setStrategy(new VayDaiHanStrategy());
        vayManager3.taoKhoanVay(vayDaiHan);


        // ==========================
        // TEST STRATEGY RISK
        // ==========================
        System.out.println("\n===== TEST STRATEGY RISK =====");

        RuiRo ruiRoTinDung = new RuiRo();
        ruiRoTinDung.setMaRuiRo("RR_TEST1");
        ruiRoTinDung.setMaKhoanVay("KV_TEST1");
        ruiRoTinDung.setChiTiet("Khach hang co so tien con no cao");
        ruiRoTinDung.setNgayPhatHien(LocalDate.now());

        RuiRoTinDungStrategy tinDungStrategy = new RuiRoTinDungStrategy();

        RuiRoManager ruiRoManager1 = new RuiRoManager();
        ruiRoManager1.setStrategy(tinDungStrategy);
        ruiRoManager1.phatHienRuiRo("AD001", ruiRoTinDung);

        System.out.println(">>> Muc do rui ro: "
                + tinDungStrategy.getMucDoRuiRo());


        RuiRo ruiRoThanhToan = new RuiRo();
        ruiRoThanhToan.setMaRuiRo("RR_TEST2");
        ruiRoThanhToan.setMaKhoanVay("KV_TEST2");
        ruiRoThanhToan.setChiTiet("Khach hang cham thanh toan");
        ruiRoThanhToan.setNgayPhatHien(LocalDate.now());

        RuiRoThanhToanStrategy thanhToanStrategy
                = new RuiRoThanhToanStrategy();

        RuiRoManager ruiRoManager2 = new RuiRoManager();
        ruiRoManager2.setStrategy(thanhToanStrategy);
        ruiRoManager2.phatHienRuiRo("AD001", ruiRoThanhToan);

        System.out.println(">>> Muc do rui ro: "
                + thanhToanStrategy.getMucDoRuiRo());


        RuiRo ruiRoHoSo = new RuiRo();
        ruiRoHoSo.setMaRuiRo("RR_TEST3");
        ruiRoHoSo.setMaKhoanVay("KV_TEST3");
        ruiRoHoSo.setChiTiet("Ho so vay thieu giay to");
        ruiRoHoSo.setNgayPhatHien(LocalDate.now());

        RuiRoHoSoStrategy hoSoStrategy
                = new RuiRoHoSoStrategy();

        RuiRoManager ruiRoManager3 = new RuiRoManager();
        ruiRoManager3.setStrategy(hoSoStrategy);
        ruiRoManager3.phatHienRuiRo("AD001", ruiRoHoSo);

        System.out.println(">>> Muc do rui ro: "
                + hoSoStrategy.getMucDoRuiRo());


        RuiRo ruiRoBaoMat = new RuiRo();
        ruiRoBaoMat.setMaRuiRo("RR_TEST4");
        ruiRoBaoMat.setMaKhoanVay("KV_TEST1");
        ruiRoBaoMat.setChiTiet("Truy cap bat thuong vao ho so khoan vay");
        ruiRoBaoMat.setNgayPhatHien(LocalDate.now());

        RuiRoBaoMatStrategy baoMatStrategy
                = new RuiRoBaoMatStrategy();

        RuiRoManager ruiRoManager4 = new RuiRoManager();
        ruiRoManager4.setStrategy(baoMatStrategy);
        ruiRoManager4.phatHienRuiRo("AD001", ruiRoBaoMat);

        System.out.println(">>> Muc do rui ro: "
                + baoMatStrategy.getMucDoRuiRo());


        // ==========================
        // TEST LOG SINGLETON
        // ==========================
        System.out.println("\n===== TEST AUDIT LOG =====");

        securityService.logout("AD001");
        securityService.showAuditLogs();

        System.out.println("\n===== KET THUC TEST =====");
    }
}