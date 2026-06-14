package strategy.vay;

import model.loan.Vay;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

public class VayTrungHanStrategy implements VayStrategy {

    @Override
    public void xuLyKhoanVay(Vay vay) {
        System.out.println("[STRATEGY-VAY] VayTrungHanStrategy is running.");

        if (vay.getHanTraNo() == null) {
            vay.setHanTraNo(vay.getNgayVay().plusMonths(24));
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
        return new BigDecimal("0.078"); // 7.8%/năm
    }

    @Override
    public BigDecimal tinhTongLaiPhaiTra(Vay vay) {
        // Ước tính lãi giảm dần (approximation)
        long soThang = ChronoUnit.MONTHS.between(vay.getNgayVay(), vay.getHanTraNo());
        BigDecimal laiSuatThang = tinhLaiSuat(vay)
                .divide(new BigDecimal(12), 6, RoundingMode.HALF_UP);

        // Lãi trung bình trên dư nợ giảm dần ≈ Gốc * lãi suất tháng * (số tháng + 1)/2
        return vay.getSoTienVay()
                .multiply(laiSuatThang)
                .multiply(new BigDecimal(soThang + 1))
                .divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal tinhKhoanTraHangThang(Vay vay) {
        BigDecimal tongGoc = vay.getSoTienVay();
        long soThang = ChronoUnit.MONTHS.between(vay.getNgayVay(), vay.getHanTraNo());
        BigDecimal gocHangThang = tongGoc.divide(new BigDecimal(soThang), 2, RoundingMode.HALF_UP);
        
        // Trả hàng tháng = Gốc/tháng + Lãi trung bình
        return gocHangThang.add(
            vay.getTongLaiPhaiTra().divide(new BigDecimal(soThang), 2, RoundingMode.HALF_UP)
        );
    }
}