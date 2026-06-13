/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility;

/**
 *
 * @author FPTSHOP
 */
public class AccountStatusHandler extends AccessHandler {

    @Override
    public boolean handle(Employee employee) {

        if (employee.isLocked()) {

            System.out.println("Tài khoản bị khóa");
            return false;
        }

        System.out.println("Tài khoản hợp lệ");

        if (nextHandler != null) {
            return nextHandler.handle(employee);
        }

        return true;
    }
}
