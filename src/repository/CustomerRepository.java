package repository;

import java.util.HashMap;
import java.util.Map;
import model.customer.Customer;

public class CustomerRepository {

    private static final Map<String, Customer> customers = new HashMap<>();

    static {
        Customer c1 = new Customer();
        c1.setMaTaiKhoan("KH001");

        Customer c2 = new Customer();
        c2.setMaTaiKhoan("KH002");

        customers.put(c1.getMaTaiKhoan(), c1);
        customers.put(c2.getMaTaiKhoan(), c2);
    }

    public Customer findById(String maTaiKhoan) {
        return customers.get(maTaiKhoan);
    }
}
