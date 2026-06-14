/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import model.loan.Vay;

/**
 * Builder for the Vay model matching the SQL table schema.
 */
public class VayBuilder {

    private Vay vay;

    private VayBuilder() {
        this.vay = new Vay();
    }

    public static VayBuilder builder() {
        return new VayBuilder();
    }

    public VayBuilder setMaKhoanVay(String maKhoanVay) {
        vay.setMaKhoanVay(maKhoanVay);
        return this;
    }

    public VayBuilder setMaTaiKhoan(String maTaiKhoan) {
        vay.setMaTaiKhoan(maTaiKhoan);
        return this;
    }

    public VayBuilder setNgayVay(LocalDate ngayVay) {
        vay.setNgayVay(ngayVay);
        return this;
    }

    public VayBuilder setSoTienVay(BigDecimal soTienVay) {
        vay.setSoTienVay(soTienVay);
        return this;
    }

    public VayBuilder setHanTraNo(LocalDate hanTraNo) {
        vay.setHanTraNo(hanTraNo);
        return this;
    }

    public Vay build() {
        return vay;
    }
}