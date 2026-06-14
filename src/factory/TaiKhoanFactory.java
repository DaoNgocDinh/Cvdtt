/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import builder.TaiKhoanBuilder;
import model.account.TaiKhoan;

/**
 * Factory for creating TaiKhoan instances with schema-aligned defaults.
 */
public class TaiKhoanFactory {

    public static TaiKhoan createAdmin(String maTaiKhoan, String hoTen, String email, String matKhau) {
        return TaiKhoanBuilder.builder()
                .setMaTaiKhoan(maTaiKhoan)
                .setHoTen(hoTen)
                .setEmail(email)
                .setMatKhau(matKhau)
                .setChucVu("Quản trị hệ thống")
                .setLocker(false)
                .setRoleName("ADMIN")
                .build();
    }

    public static TaiKhoan createNhanVien(String maTaiKhoan, String hoTen, String email, String matKhau) {
        return createNhanVien(maTaiKhoan, hoTen, email, matKhau, "Chuyên viên tín dụng", "NHANVIEN");
    }

    public static TaiKhoan createNhanVien(String maTaiKhoan, String hoTen, String email, String matKhau, String chucVu, String roleName) {
        return TaiKhoanBuilder.builder()
                .setMaTaiKhoan(maTaiKhoan)
                .setHoTen(hoTen)
                .setEmail(email)
                .setMatKhau(matKhau)
                .setChucVu(chucVu)
                .setLocker(false)
                .setRoleName(roleName)
                .build();
    }

    public static TaiKhoan createKhachHang(String maTaiKhoan, String hoTen, String email, String matKhau, String soDienThoai, String cccd, java.math.BigDecimal soTienConNo) {
        return TaiKhoanBuilder.builder()
                .setMaTaiKhoan(maTaiKhoan)
                .setHoTen(hoTen)
                .setEmail(email)
                .setMatKhau(matKhau)
                .setLocker(false)
                .setSoDienThoai(soDienThoai)
                .setCccd(cccd)
                .setSoTienConNo(soTienConNo)
                .setRoleName("KHACHHANG")
                .build();
    }
}
