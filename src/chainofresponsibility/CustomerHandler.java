/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility;

import model.customer.Customer;

/**
 *
 * @author FPTSHOP
 */
public abstract class CustomerHandler {
    protected CustomerHandler nextHandler;

    public void setNextHandler(CustomerHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean handle(Customer customer);
}
