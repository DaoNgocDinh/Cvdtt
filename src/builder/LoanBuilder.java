package builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import model.loan.Vay;

public class LoanBuilder {

    private String maKhoanVay;
    private LocalDate ngayVay;
    private BigDecimal soTienVay;
    private LocalDate hanTraNo;

    private LoanBuilder() {
    }

    public static LoanBuilder builder() {
        return new LoanBuilder();
    }

    public LoanBuilder maKhoanVay(String maKhoanVay) {
        this.maKhoanVay = maKhoanVay;
        return this;
    }

    public LoanBuilder ngayVay(LocalDate ngayVay) {
        this.ngayVay = ngayVay;
        return this;
    }

    public LoanBuilder soTienVay(BigDecimal soTienVay) {
        this.soTienVay = soTienVay;
        return this;
    }

    public LoanBuilder hanTraNo(LocalDate hanTraNo) {
        this.hanTraNo = hanTraNo;
        return this;
    }

    public Vay build() {

        Vay vay = new Vay();

        vay.setMaKhoanVay(maKhoanVay);
        vay.setNgayVay(ngayVay);
        vay.setSoTienVay(soTienVay);
        vay.setHanTraNo(hanTraNo);

        return vay;
    }
}
