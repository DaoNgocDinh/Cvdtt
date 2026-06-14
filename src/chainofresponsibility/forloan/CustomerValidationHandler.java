/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility.forloan;

import chainofresponsibility.CustomerHandler;
import model.customer.Customer;

public class CustomerValidationHandler extends CustomerHandler {

    @Override
    public boolean handle(Customer customer) {
        
        
        System.out.println("Khach hang co trong he thong");
        return true;
    }
}
