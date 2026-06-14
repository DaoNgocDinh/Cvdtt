package model.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vay {
    private String maKhoanVay;
    private String maTaiKhoan;
    private LocalDate ngayVay;
    private BigDecimal soTienVay;
    private LocalDate hanTraNo;

    // Thông tin lãi vay
    private BigDecimal laiSuat;              // Lãi suất năm
    private BigDecimal tongLaiPhaiTra;       // Tổng lãi phải trả
    private BigDecimal soTienTraHangThang;   // Khoản trả góp hàng tháng

    // Getter & Setter
    public String getMaKhoanVay() { return maKhoanVay; }
    public void setMaKhoanVay(String maKhoanVay) { this.maKhoanVay = maKhoanVay; }

    public String getMaTaiKhoan() { return maTaiKhoan; }
    public void setMaTaiKhoan(String maTaiKhoan) { this.maTaiKhoan = maTaiKhoan; }

    public LocalDate getNgayVay() { return ngayVay; }
    public void setNgayVay(LocalDate ngayVay) { this.ngayVay = ngayVay; }

    public BigDecimal getSoTienVay() { return soTienVay; }
    public void setSoTienVay(BigDecimal soTienVay) { this.soTienVay = soTienVay; }

    public LocalDate getHanTraNo() { return hanTraNo; }
    public void setHanTraNo(LocalDate hanTraNo) { this.hanTraNo = hanTraNo; }

    public BigDecimal getLaiSuat() { return laiSuat; }
    public void setLaiSuat(BigDecimal laiSuat) { this.laiSuat = laiSuat; }

    public BigDecimal getTongLaiPhaiTra() { return tongLaiPhaiTra; }
    public void setTongLaiPhaiTra(BigDecimal tongLaiPhaiTra) { this.tongLaiPhaiTra = tongLaiPhaiTra; }

    public BigDecimal getSoTienTraHangThang() { return soTienTraHangThang; }
    public void setSoTienTraHangThang(BigDecimal soTienTraHangThang) { 
        this.soTienTraHangThang = soTienTraHangThang; 
    }
}