/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import decorator.AuditLogDecorator;
import decorator.SecurityDecorator;
import model.employee.Employee;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class TestDecorator {

    public static void main(String[] args) {

        Employee employee = new Employee();
        employee.setFullName("Nguyen Van A");

        EmployeeService service
                = new EmployeeServiceImpl();

        service
                = new SecurityDecorator(service);

        service
                = new AuditLogDecorator(service);

        service.createEmployee(employee);
    }
}
