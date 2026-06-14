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
public class AccountStatusHandler extends AccessHandler {

    @Override
    public boolean handle(TaiKhoan taikhoan) {

        if (taikhoan.getLocker()) {

            System.out.println("Tai khoan bi khoa");
            return false;
        }

        System.out.println("Tai khoan hop le");

        if (nextHandler != null) {
            return nextHandler.handle(taikhoan);
        }

        return true;
    }
}
