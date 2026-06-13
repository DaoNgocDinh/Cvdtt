/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility;

/**
 *
 * @author FPTSHOP
 */
public class AuthenticationHandler extends AccessHandler {

    @Override
    public boolean handle(Employee employee) {

        if (employee == null) {
            System.out.println("Sai tài khoản hoặc mật khẩu");
            return false;
        }

        System.out.println("Xác thực thành công");

        if (nextHandler != null) {
            return nextHandler.handle(employee);
        }

        return true;
    }
}
