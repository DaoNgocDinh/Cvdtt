package model.account;

import repository.TaiKhoanRepository;

public class AuthRequest {

    public String username;
    public String password;
    public TaiKhoan account;
    public TaiKhoanRepository repository;
    public boolean isAuthenticated;
    public boolean authenticated = false;
}
