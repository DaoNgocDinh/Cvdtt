/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility;

/**
 *
 * @author FPTSHOP
 */
public class RoleValidationHandler extends AccessHandler {

    @Override
    public boolean handle(Employee employee) {

        if (employee.getRole() == null) {

            System.out.println("Chưa được cấp quyền");
            return false;
        }

        System.out.println("Vai trò: " + employee.getRole());

        if (nextHandler != null) {
            return nextHandler.handle(employee);
        }

        return true;
    }
}
