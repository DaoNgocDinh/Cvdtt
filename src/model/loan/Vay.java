/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author FPTSHOP
 */
public class Vay {

    private String maKhoanVay;
    private LocalDate ngayVay;
    private BigDecimal soTienVay;
    private LocalDate hanTraNo;

    public String getMaKhoanVay() {
        return maKhoanVay;
    }

    public void setMaKhoanVay(String maKhoanVay) {
        this.maKhoanVay = maKhoanVay;
    }

    public LocalDate getNgayVay() {
        return ngayVay;
    }

    public void setNgayVay(LocalDate ngayVay) {
        this.ngayVay = ngayVay;
    }

    public BigDecimal getSoTienVay() {
        return soTienVay;
    }

    public void setSoTienVay(BigDecimal soTienVay) {
        this.soTienVay = soTienVay;
    }

    public LocalDate getHanTraNo() {
        return hanTraNo;
    }

    public void setHanTraNo(LocalDate hanTraNo) {
        this.hanTraNo = hanTraNo;
    }
}
