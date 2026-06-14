package strategy.vay;

import model.loan.Vay;
import java.math.BigDecimal;

public interface VayStrategy {
    void xuLyKhoanVay(Vay vay);
    
    BigDecimal tinhLaiSuat(Vay vay);
    BigDecimal tinhTongLaiPhaiTra(Vay vay);
    BigDecimal tinhKhoanTraHangThang(Vay vay);
}