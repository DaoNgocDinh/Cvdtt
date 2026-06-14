package proxy;

import decorator.audit.AlertDecorator;
import decorator.audit.AuditLogger;
import decorator.audit.BasicAuditLogger;
import decorator.audit.SecurityDecorator;
import model.account.TaiKhoan;
import service.AuthenticationService;

public class AuditProxy implements AuthenticationService {

    private final AuthenticationService delegate;
    private final AuditLogger logger;

    public AuditProxy(AuthenticationService delegate) {

        this.delegate = delegate;

        this.logger
                = new AlertDecorator(
                        new SecurityDecorator(
                                new BasicAuditLogger()));
    }

    @Override
    public TaiKhoan authenticate(
            String username,
            String password) {

        logger.log(
                "login.attempt | " + username);

        TaiKhoan taiKhoan
                = delegate.authenticate(
                        username,
                        password);

        if (taiKhoan != null) {

            logger.log(
                    "login.success | " + username);

        } else {

            logger.log(
                    "login.failed | " + username);
        }

        return taiKhoan;
    }
}
