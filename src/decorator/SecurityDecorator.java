/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator;

import model.employee.Employee;
import service.EmployeeService;

/**
 *
 * @author FPTSHOP
 */
public class SecurityDecorator
        extends EmployeeServiceDecorator {

    public SecurityDecorator(
            EmployeeService service) {

        super(service);
    }

    @Override
    public void createEmployee(Employee employee) {

        System.out.println(
                "[Security] Kiểm tra quyền Admin"
        );

        service.createEmployee(employee);
    }
}
