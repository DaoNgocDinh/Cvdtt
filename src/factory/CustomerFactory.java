/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import builder.CustomerBuilder;
import model.customer.Customer;

/**
 *
 * @author FPTSHOP
 */
public class CustomerFactory {

    public static Customer createIndividualCustomer(String id, String name, String email, String phone, String address, String identityNumber) {
        return CustomerBuilder.builder()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .setPhone(phone)
                .setAddress(address)
                .setCustomerType("Individual")
                .setIdentityNumber(identityNumber)
                .setActive(true)
                .setRegisteredDate(java.time.LocalDate.now())
                .build();
    }

    public static Customer createBusinessCustomer(String id, String name, String email, String phone, String address, String identityNumber) {
        return CustomerBuilder.builder()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .setPhone(phone)
                .setAddress(address)
                .setCustomerType("Business")
                .setIdentityNumber(identityNumber)
                .setActive(true)
                .setRegisteredDate(java.time.LocalDate.now())
                .build();
    }
}
