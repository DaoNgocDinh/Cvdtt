package strategy.vay;

import model.loan.Vay;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

public class VayDaiHanStrategy implements VayStrategy {

    @Override
    public void xuLyKhoanVay(Vay vay) {
        System.out.println("[STRATEGY-VAY] VayDaiHanStrategy is running.");

        if (vay.getHanTraNo() == null) {
            vay.setHanTraNo(vay.getNgayVay().plusMonths(60));
        }

        BigDecimal laiSuat = tinhLaiSuat(vay);
        vay.setLaiSuat(laiSuat);

        BigDecimal tongLai = tinhTongLaiPhaiTra(vay);
        vay.setTongLaiPhaiTra(tongLai);

        BigDecimal traHangThang = tinhKhoanTraHangThang(vay);
        vay.setSoTienTraHangThang(traHangThang);
    }

    @Override
    public BigDecimal tinhLaiSuat(Vay vay) {
        return new BigDecimal("0.072"); // 7.2%/năm
    }

    @Override
    public BigDecimal tinhTongLaiPhaiTra(Vay vay) {
        BigDecimal pmt = tinhKhoanTraHangThang(vay); // Tính trước PMT
        long soThang = ChronoUnit.MONTHS.between(vay.getNgayVay(), vay.getHanTraNo());
        return pmt.multiply(new BigDecimal(soThang)).subtract(vay.getSoTienVay());
    }

    @Override
    public BigDecimal tinhKhoanTraHangThang(Vay vay) {
        long n = ChronoUnit.MONTHS.between(vay.getNgayVay(), vay.getHanTraNo());
        BigDecimal r = tinhLaiSuat(vay)
                .divide(new BigDecimal(12), 8, RoundingMode.HALF_UP); // lãi tháng

        if (r.compareTo(BigDecimal.ZERO) == 0) {
            return vay.getSoTienVay().divide(new BigDecimal(n), 2, RoundingMode.HALF_UP);
        }

        // Công thức Annuity: PMT = P * r * (1+r)^n / ((1+r)^n - 1)
        BigDecimal onePlusR = BigDecimal.ONE.add(r);
        BigDecimal power = onePlusR.pow((int) n);

        BigDecimal numerator = vay.getSoTienVay().multiply(r).multiply(power);
        BigDecimal denominator = power.subtract(BigDecimal.ONE);

        return numerator.divide(denominator, 2, RoundingMode.HALF_UP);
    }
}