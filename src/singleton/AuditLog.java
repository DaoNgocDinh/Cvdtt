package singleton;

import java.time.LocalDateTime;

public class AuditLog {

    private String maTaiKhoan;
    private String hanhDong;
    private LocalDateTime thoiGian;

    public AuditLog(String maTaiKhoan, String hanhDong) {
        this.maTaiKhoan = maTaiKhoan;
        this.hanhDong = hanhDong;
        this.thoiGian = LocalDateTime.now();
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    @Override
    public String toString() {
        return "[" + thoiGian + "] "
                + "MaTaiKhoan=" + maTaiKhoan
                + ", HanhDong=" + hanhDong;
    }
}