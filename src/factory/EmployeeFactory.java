/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import builder.EmployeeBuilder;
import model.employee.Employee;
import model.employee.EmployeeRole;
import model.employee.EmployeeState;

/**
 *
 * @author FPTSHOP
 */
public class EmployeeFactory {

    public static Employee createAdmin(String id, String username, String password, String fullName, String email, String phone) {
        return EmployeeBuilder.builder()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setRole(EmployeeRole.ADMIN.name())
                .setDepartment("Administration")
                .setState(EmployeeState.ACTIVE)
                .build();
    }

    public static Employee createEmployee(String id, String username, String password, String fullName, String email, String phone, String department) {
        return EmployeeBuilder.builder()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setRole(EmployeeRole.EMPLOYEE.name())
                .setDepartment(department)
                .setState(EmployeeState.ACTIVE)
                .build();
    }
}
