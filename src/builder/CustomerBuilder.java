/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import java.time.LocalDate;
import model.customer.Customer;

/**
 *
 * @author FPTSHOP
 */
public class CustomerBuilder {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String customerType;
    private String identityNumber;
    private boolean active;
    private LocalDate registeredDate;

    private CustomerBuilder() {
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public CustomerBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder address(String address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder customerType(String customerType) {
        this.customerType = customerType;
        return this;
    }

    public CustomerBuilder identityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
        return this;
    }

    public CustomerBuilder active(boolean active) {
        this.active = active;
        return this;
    }

    public CustomerBuilder registeredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCustomerType(customerType);
        customer.setIdentityNumber(identityNumber);
        customer.setActive(active);
        customer.setRegisteredDate(registeredDate);
        return customer;
    }
}
