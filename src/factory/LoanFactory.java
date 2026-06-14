package factory;

import builder.VayBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import model.loan.Vay;

/**
 * Factory class for creating loan objects
 */
public class LoanFactory {

    public static Vay createLoan(
            String maKhoanVay,
            String maTaiKhoan,
            double soTienVay,
            int termMonths) {

        LocalDate ngayVay = LocalDate.now();
        LocalDate hanTraNo = ngayVay.plusMonths(termMonths);

        return VayBuilder.builder()
                .setMaKhoanVay(maKhoanVay)
                .setMaTaiKhoan(maTaiKhoan)
                .setNgayVay(ngayVay)
                .setSoTienVay(BigDecimal.valueOf(soTienVay))
                .setHanTraNo(hanTraNo)
                .build();
    }
}