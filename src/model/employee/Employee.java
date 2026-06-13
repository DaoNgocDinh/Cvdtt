package model.employee;

public class Employee {

    private String employeeId;
    private String fullName;
    private String email;
    private String username;
    private String password;

    public Employee(String employeeId,
                    String fullName,
                    String email,
                    String username,
                    String password) {

        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}