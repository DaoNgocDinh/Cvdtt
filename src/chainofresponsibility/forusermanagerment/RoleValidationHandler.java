package chainofresponsibility.forusermanagerment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import chainofresponsibility.AccessHandler;
import model.employee.Employee;
/**
 *
 * @author FPTSHOP
 */
public class RoleValidationHandler extends AccessHandler {

    @Override
    public boolean handle(Employee employee) {

        if (employee.getRole() == null) {

            System.out.println("Chua duoc cap quyen");
            return false;
        }

        System.out.println("Vai tro: " + employee.getRole());

        if (nextHandler != null) {
            return nextHandler.handle(employee);
        }

        return true;
    }
}
