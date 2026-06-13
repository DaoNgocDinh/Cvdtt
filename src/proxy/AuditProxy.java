/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proxy;

import model.account.TaiKhoan;
import service.AuthenticationService;

/**
 *
 * @author FPTSHOP
 */
public class AuditProxy implements AuthenticationService {

    private final AuthenticationService delegate;

    public AuditProxy(AuthenticationService delegate) {
        this.delegate = delegate;
    }

    @Override
    public TaiKhoan authenticate(String username, String password) {
        System.out.println("AUDIT PROXY: Login attempt for username='" + username + "'.");
        TaiKhoan taiKhoan = delegate.authenticate(username, password);
        if (taiKhoan != null) {
            System.out.println("AUDIT PROXY: Login successful for username='" + username + "'.");
        } else {
            System.out.println("AUDIT PROXY: Login failed for username='" + username + "'.");
        }
        return taiKhoan;
    }
}
