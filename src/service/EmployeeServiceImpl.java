/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.employee.Employee;

/**
 *
 * @author FPTSHOP
 */
public class EmployeeServiceImpl
        implements EmployeeService {

    @Override
    public void createEmployee(Employee employee) {

        System.out.println(
                "Lưu nhân viên vào Database"
        );
    }
}
