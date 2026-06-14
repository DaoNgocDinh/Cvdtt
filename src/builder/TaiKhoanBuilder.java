/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import model.account.TaiKhoan;

/**
 * Builder for TaiKhoan model using schema-aligned setters.
 */
public class TaiKhoanBuilder {

    private TaiKhoan taiKhoan;

    private TaiKhoanBuilder() {
        this.taiKhoan = new TaiKhoan();
    }

    public static TaiKhoanBuilder builder() {
        return new TaiKhoanBuilder();
    }

    public TaiKhoanBuilder setMaTaiKhoan(String maTaiKhoan) {
        taiKhoan.setMaTaiKhoan(maTaiKhoan);
        return this;
    }

    public TaiKhoanBuilder setHoTen(String hoTen) {
        taiKhoan.setHoTen(hoTen);
        return this;
    }

    public TaiKhoanBuilder setEmail(String email) {
        taiKhoan.setEmail(email);
        return this;
    }

    public TaiKhoanBuilder setMatKhau(String matKhau) {
        taiKhoan.setMatKhau(matKhau);
        return this;
    }

    public TaiKhoanBuilder setChucVu(String chucVu) {
        taiKhoan.setChucVu(chucVu);
        return this;
    }

    public TaiKhoanBuilder setLocker(Boolean locker) {
        taiKhoan.setLocker(locker);
        return this;
    }

    public TaiKhoanBuilder setSoDienThoai(String soDienThoai) {
        taiKhoan.setSoDienThoai(soDienThoai);
        return this;
    }

    public TaiKhoanBuilder setCccd(String cccd) {
        taiKhoan.setCccd(cccd);
        return this;
    }

    public TaiKhoanBuilder setSoTienConNo(java.math.BigDecimal soTienConNo) {
        taiKhoan.setSoTienConNo(soTienConNo);
        return this;
    }

    public TaiKhoanBuilder setRoleName(String roleName) {
        taiKhoan.setRoleName(roleName);
        return this;
    }

    public TaiKhoan build() {
        return taiKhoan;
    }
}
