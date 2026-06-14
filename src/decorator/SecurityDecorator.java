/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator;

import model.account.TaiKhoan;
import service.EmployeeService;

/**
 *
 * @author FPTSHOP
 */
public class SecurityDecorator
        extends EmployeeServiceDecorator {

    public SecurityDecorator(
            EmployeeService service) {

        super(service);
    }

    @Override
    public void createEmployee(TaiKhoan taikhoan) {

        System.out.println(
                "[Security] Kiểm tra quyền Admin"
        );

        service.createEmployee(taikhoan);
    }
}
