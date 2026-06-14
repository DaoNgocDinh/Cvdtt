package factory;

import builder.LoanBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import model.loan.Vay;

public class LoanFactory {

    public static Vay createPersonalLoan(
            String maKhoanVay,
            BigDecimal soTienVay,
            int soThangVay) {

        return LoanBuilder.builder()
                .maKhoanVay(maKhoanVay)
                .ngayVay(LocalDate.now())
                .soTienVay(soTienVay)
                .hanTraNo(LocalDate.now().plusMonths(soThangVay))
                .build();
    }
}
