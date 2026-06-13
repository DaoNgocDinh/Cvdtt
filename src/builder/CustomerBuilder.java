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

    private Customer customer;

    private CustomerBuilder() {
        this.customer = new Customer();
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public CustomerBuilder setId(String id) {
        customer.setId(id);
        return this;
    }

    public CustomerBuilder setName(String name) {
        customer.setName(name);
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        customer.setEmail(email);
        return this;
    }

    public CustomerBuilder setPhone(String phone) {
        customer.setPhone(phone);
        return this;
    }

    public CustomerBuilder setAddress(String address) {
        customer.setAddress(address);
        return this;
    }

    public CustomerBuilder setCustomerType(String customerType) {
        customer.setCustomerType(customerType);
        return this;
    }

    public CustomerBuilder setIdentityNumber(String identityNumber) {
        customer.setIdentityNumber(identityNumber);
        return this;
    }

    public CustomerBuilder setActive(boolean active) {
        customer.setActive(active);
        return this;
    }

    public CustomerBuilder setRegisteredDate(LocalDate registeredDate) {
        customer.setRegisteredDate(registeredDate);
        return this;
    }

    public Customer build() {
        return customer;
    }
}
