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

    private String id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private String department;
    private EmployeeState state;
    private LocalDate hiredDate;

    private EmployeeBuilder() {
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public EmployeeBuilder id(String id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder username(String username) {
        this.username = username;
        return this;
    }

    public EmployeeBuilder password(String password) {
        this.password = password;
        return this;
    }

    public EmployeeBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public EmployeeBuilder email(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public EmployeeBuilder role(String role) {
        this.role = role;
        return this;
    }

    public EmployeeBuilder department(String department) {
        this.department = department;
        return this;
    }

    public EmployeeBuilder state(EmployeeState state) {
        this.state = state;
        return this;
    }

    public EmployeeBuilder hiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
        return this;
    }

    public Employee build() {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setFullName(fullName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setRole(role);
        employee.setDepartment(department);
        employee.setState(state);
        employee.setHiredDate(hiredDate);
        return employee;
    }
}
