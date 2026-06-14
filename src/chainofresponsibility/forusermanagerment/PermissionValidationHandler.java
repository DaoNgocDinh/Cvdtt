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
public class PermissionValidationHandler extends AccessHandler {

    @Override
    public boolean handle(TaiKhoan taikhoan) {

        System.out.println("Kiem tra quyen truy cap");

        return true;
    }
}
