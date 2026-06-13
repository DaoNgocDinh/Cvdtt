package facade;

import command.employee.CreateEmployeeCommand;
import factory.EmployeeFactory;
import model.employee.Employee;
import service.EmployeeService;

public class EmployeeFacade {

    private EmployeeService employeeService;

    public EmployeeFacade() {
        employeeService = new EmployeeService();
    }

    public void createAdmin(
            String id,
            String username,
            String password,
            String fullName,
            String email,
            String phone) {

        Employee employee =
                EmployeeFactory.createAdmin(
                        id,
                        username,
                        password,
                        fullName,
                        email,
                        phone);

        CreateEmployeeCommand command =
                new CreateEmployeeCommand(
                        employeeService,
                        employee);

        command.execute();
    }

    public void createEmployee(
            String id,
            String username,
            String password,
            String fullName,
            String email,
            String phone,
            String department) {

        Employee employee =
                EmployeeFactory.createEmployee(
                        id,
                        username,
                        password,
                        fullName,
                        email,
                        phone,
                        department);

        CreateEmployeeCommand command =
                new CreateEmployeeCommand(
                        employeeService,
                        employee);

        command.execute();
    }
}