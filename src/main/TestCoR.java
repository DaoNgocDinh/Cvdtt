/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import chainofresponsibility.forusermanagerment.PermissionValidationHandler;
import chainofresponsibility.forusermanagerment.RoleValidationHandler;
import chainofresponsibility.AccessHandler;
import chainofresponsibility.forusermanagerment.AccountStatusHandler;
import chainofresponsibility.forusermanagerment.AuthenticationHandler;
import chainofresponsibility.forusermanagerment.PermissionValidationHandler;
import chainofresponsibility.forusermanagerment.RoleValidationHandler;
import model.account.TaiKhoan;
public class TestCoR {

    public static void main(String[] args) {

        TaiKhoan employee = new TaiKhoan();

        employee.setHoTen("admin");
        employee.setRoleName("ADMIN");
        employee.setLocker(Boolean.FALSE);

        AccessHandler auth
                = new AuthenticationHandler();

        AccessHandler status
                = new AccountStatusHandler();

        AccessHandler role
                = new RoleValidationHandler();

        AccessHandler permission
                = new PermissionValidationHandler();

        auth.setNextHandler(status);
        status.setNextHandler(role);
        role.setNextHandler(permission);

        boolean result = auth.handle(employee);

        System.out.println("Login Result = " + result);
    }
}
