/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility;
import model.employee.Employee;
/**
 *
 * @author FPTSHOP
 */
public class PermissionValidationHandler extends AccessHandler {

    @Override
    public boolean handle(Employee employee) {

        System.out.println("Kiểm tra quyền truy cập");

        return true;
    }
}
