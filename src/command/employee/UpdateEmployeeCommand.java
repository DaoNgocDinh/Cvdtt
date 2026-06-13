package command.employee;

import command.Command;
import model.employee.Employee;
import service.EmployeeService;

public class UpdateEmployeeCommand implements Command {

    private EmployeeService service;
    private Employee employee;

    public UpdateEmployeeCommand(
            EmployeeService service,
            Employee employee) {

        this.service = service;
        this.employee = employee;
    }

    @Override
    public void execute() {
        service.updateEmployee(employee);
    }
}