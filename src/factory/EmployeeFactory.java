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
                .id(id)
                .username(username)
                .password(password)
                .fullName(fullName)
                .email(email)
                .phone(phone)
                .role(EmployeeRole.ADMIN.name())
                .department("Administration")
                .state(EmployeeState.ACTIVE)
                .build();
    }

    public static Employee createEmployee(String id, String username, String password, String fullName, String email, String phone, String department) {
        return EmployeeBuilder.builder()
                .id(id)
                .username(username)
                .password(password)
                .fullName(fullName)
                .email(email)
                .phone(phone)
                .role(EmployeeRole.EMPLOYEE.name())
                .department(department)
                .state(EmployeeState.ACTIVE)
                .build();
    }
}
