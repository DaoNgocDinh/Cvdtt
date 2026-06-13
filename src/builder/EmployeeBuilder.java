/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import java.time.LocalDate;
import model.employee.Employee;
import model.employee.EmployeeState;

/**
 *
 * @author FPTSHOP
 */
public class EmployeeBuilder {

    private Employee employee;

    private EmployeeBuilder() {
        this.employee = new Employee();
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public EmployeeBuilder setId(String id) {
        employee.setId(id);
        return this;
    }

    public EmployeeBuilder setUsername(String username) {
        employee.setUsername(username);
        return this;
    }

    public EmployeeBuilder setPassword(String password) {
        employee.setPassword(password);
        return this;
    }

    public EmployeeBuilder setFullName(String fullName) {
        employee.setFullName(fullName);
        return this;
    }

    public EmployeeBuilder setEmail(String email) {
        employee.setEmail(email);
        return this;
    }

    public EmployeeBuilder setPhone(String phone) {
        employee.setPhone(phone);
        return this;
    }

    public EmployeeBuilder setRole(String role) {
        employee.setRole(role);
        return this;
    }

    public EmployeeBuilder setDepartment(String department) {
        employee.setDepartment(department);
        return this;
    }

    public EmployeeBuilder setState(EmployeeState state) {
        employee.setState(state);
        return this;
    }

    public EmployeeBuilder setHiredDate(LocalDate hiredDate) {
        employee.setHiredDate(hiredDate);
        return this;
    }

    public Employee build() {
        return employee;
    }
}
