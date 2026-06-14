package decorator.audit;

import singleton.AuditLogManager;

public class BasicAuditLogger
        implements AuditLogger {

    @Override
    public void log(String message) {

        System.out.println(
                "[AUDIT] " + message);

        AuditLogManager
                .getInstance()
                .writeLog(
                        "ADMIN",
                        message
                );
    }
}
