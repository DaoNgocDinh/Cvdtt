package factory;

import model.employee.Employee;

public abstract class EmployeeFactory {

    public abstract Employee createEmployee(
            String id,
            String name,
            String email);
}