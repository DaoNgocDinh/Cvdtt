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
public class AuditLogDecorator
        extends EmployeeServiceDecorator {

    public AuditLogDecorator(
            EmployeeService service) {

        super(service);
    }

    @Override
    public void createEmployee(TaiKhoan taikhoan) {

        service.createEmployee(taikhoan);

        System.out.println(
                "[Audit] Ghi log tạo nhân viên"
        );
    }
}
