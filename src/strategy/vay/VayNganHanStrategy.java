package strategy.vay;

import model.loan.Vay;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

public class VayNganHanStrategy implements VayStrategy {

    @Override
    public void xuLyKhoanVay(Vay vay) {
        System.out.println("[STRATEGY-VAY] VayNganHanStrategy is running.");

        if (vay.getHanTraNo() == null) {
            vay.setHanTraNo(vay.getNgayVay().plusMonths(6));
        }

        BigDecimal laiSuat = tinhLaiSuat(vay);
        vay.setLaiSuat(laiSuat);

        BigDecimal tongLai = tinhTongLaiPhaiTra(vay);
        vay.setTongLaiPhaiTra(tongLai);

        BigDecimal traHangThang = tinhKhoanTraHangThang(vay);
        vay.setSoTienTraHangThang(traHangThang);

        System.out.println("[STRATEGY-VAY] " + getMoTaCongThuc());
    }

    @Override
    public BigDecimal tinhLaiSuat(Vay vay) {
        return new BigDecimal("0.085"); // 8.5%/năm
    }

    @Override
    public BigDecimal tinhTongLaiPhaiTra(Vay vay) {
        long soThang = ChronoUnit.MONTHS.between(vay.getNgayVay(), vay.getHanTraNo());
        BigDecimal laiSuatThang = tinhLaiSuat(vay)
                .divide(new BigDecimal(12), 6, RoundingMode.HALF_UP);

        // Lãi đơn: Gốc * lãi suất tháng * số tháng
        return vay.getSoTienVay()
                .multiply(laiSuatThang)
                .multiply(new BigDecimal(soThang));
    }

    @Override
    public BigDecimal tinhKhoanTraHangThang(Vay vay) {
        BigDecimal tongPhaiTra = vay.getSoTienVay().add(vay.getTongLaiPhaiTra());
        long soThang = ChronoUnit.MONTHS.between(vay.getNgayVay(), vay.getHanTraNo());
        return tongPhaiTra.divide(new BigDecimal(soThang), 2, RoundingMode.HALF_UP);
    }

    @Override
    public String getMoTaCongThuc() {
        return "Công thức: Lãi đơn (Simple Interest)";
    }
}