/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.account.TaiKhoan;
import repository.TaiKhoanRepository;

/**
 *
 * @author FPTSHOP
 */
public class TaiKhoanAuthenticationService implements AuthenticationService {

    private final TaiKhoanRepository repository;

    public TaiKhoanAuthenticationService(TaiKhoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public TaiKhoan authenticate(String username, String password) {
        // Hiện tại dữ liệu mẫu không chứa mật khẩu, nên chỉ kiểm tra username tồn tại.
        // Bạn có thể bổ sung trường Password vào bảng TaiKhoan nếu cần xác thực thật.
        return repository.findByUsername(username);
    }
}
