<<<<<<< HEAD:src/main/test.java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;



import repository.VayRepository;

public class test {

    public static void main(String[] args) {
        System.out.println(VayRepository.class);
    }
}
=======
package factory;

import model.employee.Employee;

public abstract class EmployeeFactory {

    public abstract Employee createEmployee(
            String id,
            String name,
            String email);
}
>>>>>>> 5794d829815fc801a43fa85d2b050bbb9aeb2761:src/factory/EmployeeFactory.java
