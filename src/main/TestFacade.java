/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import builder.TaiKhoanBuilder;
import facade.TaiKhoanFacade;
import model.account.TaiKhoan;

/**
 *
 * @author FPTSHOP
 */
public class TestFacade {

    public static void main(String[] args) {
        TaiKhoanFacade facade = new TaiKhoanFacade();

        TaiKhoan taiKhoan
                = TaiKhoanBuilder.builder()
                        .setMaTaiKhoan("TK001")
                        .setHoTen("Võ Hoàng Nhật Linh xinh iu")
                        .setEmail("adminnew@gmail.com")
                        .setMatKhau("999999")
                        .setChucVu("Quản trị hệ thống")
                        .setLocker(false)
                        .build();

        facade.updateTaiKhoan(taiKhoan);
    }
}
