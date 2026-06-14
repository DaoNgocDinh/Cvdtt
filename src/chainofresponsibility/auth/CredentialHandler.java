/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility.auth;

import model.account.AuthRequest;
import cache.LoginAttemptCache;
import model.account.TaiKhoan;

public class CredentialHandler extends AuthHandler {

    @Override
    public boolean handle(AuthRequest request) {

        String username = request.username;
        String password = request.password;
        TaiKhoan account = request.account;

        if (account == null) {
            System.out.println("[CoR]: Tai khoan khong ton tai");
            return false;
        }
            System.out.println("[CoR]: Tai khoan co ton tai");

        if (!account.getMatKhau().equals(password)) {

            LoginAttemptCache.increase(username);

            int attempts = LoginAttemptCache.get(username);

            System.out.println("[CoR]: Sai mat khau lan: " + attempts);

            if (attempts >= 5) {
                account.setLocker(true);
                request.repository.updateLockStatus(
                        account.getMaTaiKhoan(),
                        true
                );

                System.out.println("[CoR]: Tai khoan da bi khoa do nhap sai qua nhieu lan");
            }

            return false;
        }
        LoginAttemptCache.reset(username);

        return handleNext(request);
    }
}
