/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.subjects;

import model.account.TaiKhoan;
import observer.Subject;
import service.AuthenticationService;

/**
 *
 * @author FPTSHOP
 */
public class LoginService extends Subject {

    private final AuthenticationService authenticationService;

    public LoginService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public TaiKhoan login(String username, String password) {
        TaiKhoan taiKhoan = authenticationService.authenticate(username, password);
        if (taiKhoan != null) {
            notifyObservers("login.success", taiKhoan);
        } else {
            notifyObservers("login.failure", username);
        }
        return taiKhoan;
    }
}
