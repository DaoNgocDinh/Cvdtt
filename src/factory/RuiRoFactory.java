/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import builder.RuiRoBuilder;
import model.risk.RuiRo;

/**
 * Factory for creating RuiRo objects matching SQL data.
 */
public class RuiRoFactory {

    public static RuiRo createRuiRo(String maRuiRo, String maKhoanVay, String tenRuiRo, String loaiRuiRo, String chiTiet, java.time.LocalDate ngayPhatHien) {
        return RuiRoBuilder.builder()
                .setMaRuiRo(maRuiRo)
                .setMaKhoanVay(maKhoanVay)
                .setTenRuiRo(tenRuiRo)
                .setLoaiRuiRo(loaiRuiRo)
                .setChiTiet(chiTiet)
                .setNgayPhatHien(ngayPhatHien)
                .build();
    }
}
