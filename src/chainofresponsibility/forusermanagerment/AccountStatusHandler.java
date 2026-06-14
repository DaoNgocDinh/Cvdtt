/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility.forusermanagerment;

import chainofresponsibility.AccessHandler;
import model.employee.Employee;
import model.employee.EmployeeState;

/**
 *
 * @author FPTSHOP
 */
public class AccountStatusHandler extends AccessHandler {

    @Override
    public boolean handle(Employee employee) {

        if (employee.getState() == EmployeeState.SUSPENDED) {

            System.out.println("Tai khoan bi khoa");
            return false;
        }

        System.out.println("Tai khoan hop le");

        if (nextHandler != null) {
            return nextHandler.handle(employee);
        }

        return true;
    }
}
