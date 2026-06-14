package service;

import chainofresponsibility.risk.*;
import decorator.audit.AlertDecorator;
import decorator.audit.AuditLogger;
import decorator.audit.BasicAuditLogger;
import decorator.audit.SecurityDecorator;
import java.math.BigDecimal;
import java.util.List;
import model.account.TaiKhoan;
import model.loan.Vay;
import repository.TaiKhoanRepository;
import repository.VayRepository;

public class VayService {

    private VayRepository vayRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private AuditLogger logger;

    public VayService() {

        vayRepository = new VayRepository();
        taiKhoanRepository = new TaiKhoanRepository();

        logger
                = new AlertDecorator(
                        new SecurityDecorator(
                                new BasicAuditLogger()));
    }

    public List<Vay> getAllLoans() {
        return vayRepository.findAll();
    }

    public void createVay(Vay vay) {

        // 1. Check null object
        if (vay == null) {
            throw new IllegalArgumentException("Khoản vay không được null");
        }

        // 2. Check mã tài khoản
        if (vay.getMaTaiKhoan() == null || vay.getMaTaiKhoan().trim().isEmpty()) {
            throw new IllegalArgumentException("Mã tài khoản không hợp lệ");
        }

        TaiKhoan khachHang
                = taiKhoanRepository.findById(vay.getMaTaiKhoan());

        if (khachHang == null) {
            throw new IllegalStateException("Không tìm thấy tài khoản: " + vay.getMaTaiKhoan());
        }

        // 3. Check số tiền vay
        if (vay.getSoTienVay() == null) {
            throw new IllegalArgumentException("Số tiền vay không được null");
        }

        if (vay.getSoTienVay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Số tiền vay phải lớn hơn 0");
        }

        // 4. Check ngày vay
        if (vay.getNgayVay() == null) {
            throw new IllegalArgumentException("Ngày vay không được null");
        }

        if (vay.getHanTraNo() == null) {
            throw new IllegalArgumentException("Hạn trả nợ không được null");
        }

        if (vay.getHanTraNo().isBefore(vay.getNgayVay())) {
            throw new IllegalArgumentException("Hạn trả nợ phải sau ngày vay");
        }
        if (vayRepository.existsById(vay.getMaKhoanVay())) {
            throw new IllegalStateException("Mã khoản vay đã tồn tại");
        }

        // 5. Lưu DB
        try {
            vayRepository.save(vay);

            RiskContext context
                    = new RiskContext();

            context.vay = vay;
            context.khachHang = khachHang;

            System.out.println("BAT DAU DANH GIA RUI RO");

            new RiskChainManager()
                    .evaluate(context);

            System.out.println("KET THUC DANH GIA RUI RO");

            logger.log(
                    "loan.created | "
                    + vay.getMaKhoanVay()
                    + " | "
                    + vay.getMaTaiKhoan()
                    + " | "
                    + vay.getSoTienVay());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu khoản vay: " + e.getMessage(), e);
        }
    }
}
