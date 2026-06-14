package decorator.audit;

public class BasicAuditLogger
        implements AuditLogger {

    @Override
    public void log(String message) {

        System.out.println(
                "[AUDIT] " + message);
    }
}
