package service;

import model.account.TaiKhoan;

public interface AuthenticationService {

    TaiKhoan authenticate(String username, String password);
    
}
