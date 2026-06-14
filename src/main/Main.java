package main;

import view.auth.LoginFrame;
import facade.TaiKhoanFacade;
import builder.TaiKhoanBuilder;
import java.util.Scanner;
import service.TaiKhoanService;
import view.auth.LoginFrame;
import model.account.TaiKhoan;

public class Main {

    public static void main(String[] args) {
        TaiKhoanFacade facade = new TaiKhoanFacade();

        TaiKhoan taiKhoan =
                TaiKhoanBuilder.builder()
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