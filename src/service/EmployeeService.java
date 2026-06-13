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
public class EmployeeService {

    public void createEmployee(Employee employee){
        System.out.println("Create Employee");
    }

    public void updateEmployee(Employee employee){
        System.out.println("Update Employee");
    }

    public void lockEmployee(String id){
        System.out.println("Lock Employee");
    }

    public void deleteEmployee(String id){
        System.out.println("Delete Employee");
    }
}
