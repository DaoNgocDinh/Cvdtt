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
            return false;
        }

        if (!account.getMatKhau().equals(password)) {

            LoginAttemptCache.increase(username);

            int attempts = LoginAttemptCache.get(username);

            System.out.println("FAILED: " + attempts);

            if (attempts >= 5) {
                account.setLocker(true);
                request.repository.updateLockStatus(
                        account.getMaTaiKhoan(),
                        true
                );

                System.out.println("ACCOUNT LOCKED");
            }

            return false;
        }

        // ✅ đúng mật khẩu → reset cache
        LoginAttemptCache.reset(username);

        return handleNext(request);
    }
}
