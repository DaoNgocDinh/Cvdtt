/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.risk;

import java.time.LocalDate;

/**
 *
 * @author FPTSHOP
 */
public class RuiRo {

    private String maRuiRo;
    private String maKhoanVay;
    private String tenRuiRo;
    private String loaiRuiRo;
    private String chiTiet;
    private LocalDate ngayPhatHien;

    public String getMaRuiRo() {
        return maRuiRo;
    }

    public void setMaRuiRo(String maRuiRo) {
        this.maRuiRo = maRuiRo;
    }

    public String getMaKhoanVay() {
        return maKhoanVay;
    }

    public void setMaKhoanVay(String maKhoanVay) {
        this.maKhoanVay = maKhoanVay;
    }

    public String getTenRuiRo() {
        return tenRuiRo;
    }

    public void setTenRuiRo(String tenRuiRo) {
        this.tenRuiRo = tenRuiRo;
    }

    public String getLoaiRuiRo() {
        return loaiRuiRo;
    }

    public void setLoaiRuiRo(String loaiRuiRo) {
        this.loaiRuiRo = loaiRuiRo;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public LocalDate getNgayPhatHien() {
        return ngayPhatHien;
    }

    public void setNgayPhatHien(LocalDate ngayPhatHien) {
        this.ngayPhatHien = ngayPhatHien;
    }
}
