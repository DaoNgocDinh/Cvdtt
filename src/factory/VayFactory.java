/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import builder.VayBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import model.loan.Vay;

/**
 * Factory for creating Vay objects with common defaults.
 */
public class VayFactory {

    public static Vay createVay(String maKhoanVay, String maTaiKhoan, BigDecimal soTienVay, LocalDate ngayVay, LocalDate hanTraNo) {
        return VayBuilder.builder()
                .setMaKhoanVay(maKhoanVay)
                .setMaTaiKhoan(maTaiKhoan)
                .setNgayVay(ngayVay)
                .setSoTienVay(soTienVay)
                .setHanTraNo(hanTraNo)
                .build();
    }
}
