/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility.forusermanagerment;

import chainofresponsibility.AccessHandler;
import model.account.TaiKhoan;

/**
 *
 * @author FPTSHOP
 */
public class AuthenticationHandler extends AccessHandler {

    @Override
    public boolean handle(TaiKhoan taikhoan) {

        if (taikhoan == null) {
            System.out.println("Sai tai khoan hoac mat khau");
            return false;
        }

        System.out.println("Xac thuc thanh cong");

        if (nextHandler != null) {
            return nextHandler.handle(taikhoan);
        }

        return true;
    }
}
