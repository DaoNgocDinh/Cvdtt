package service;

import model.account.TaiKhoan;

public class EmployeeServiceImpl
        implements EmployeeService {

    @Override
    public void createEmployee(TaiKhoan taikhoan) {
        if ("NHANVIEN".equalsIgnoreCase(taikhoan.getRoleName())) {
            System.out.println(
                    "Lưu nhân viên vào Database"
            );
        }
    }
}
