package service;

import model.account.TaiKhoan;
import model.account.AuthRequest;
import repository.TaiKhoanRepository;
import chainofresponsibility.auth.*;

public class TaiKhoanAuthenticationService implements AuthenticationService {

    private final TaiKhoanRepository repository;
    private final AuthHandler chain;

    public TaiKhoanAuthenticationService(TaiKhoanRepository repository) {

        this.repository = repository;

        AuthHandler c1 = new CredentialHandler();
        AuthHandler c2 = new AccountStatusHandler();
        AuthHandler c3 = new LoginAttemptHandler();

        c1.setNext(c2);
        c2.setNext(c3);

        this.chain = c1;
    }

    @Override
    public TaiKhoan authenticate(String username, String password) {

        TaiKhoan account = repository.findByUsername(username);

        AuthRequest req = new AuthRequest();
        req.username = username;
        req.password = password;
        req.account = account;
        req.repository = repository;

        chain.handle(req);

        if (req.account == null) {
            return null;
        }
        if (req.account.getLocker()) {
            return null;
        }
        if (req.account.getFailedAttempts() >= 5) {
            return null;
        }

        if (!req.account.getMatKhau().equals(password)) {
            return null;
        }

        return account;
    }
}
