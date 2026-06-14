package chainofresponsibility.forusermanagerment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import chainofresponsibility.AccessHandler;
import model.account.TaiKhoan;
/**
 *
 * @author FPTSHOP
 */
public class RoleValidationHandler extends AccessHandler {

    @Override
    public boolean handle(TaiKhoan taikhoan) {

        if (taikhoan.getRoleName() == "ADMIN") {

            System.out.println("Chua duoc cap quyen");
            return false;
        }

        System.out.println("Vai tro: " + taikhoan.getRoleName());

        if (nextHandler != null) {
            return nextHandler.handle(taikhoan);
        }

        return true;
    }
}
