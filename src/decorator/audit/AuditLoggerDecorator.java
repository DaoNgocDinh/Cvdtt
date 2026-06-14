package decorator.audit;

public abstract class AuditLoggerDecorator
        implements AuditLogger {

    protected AuditLogger logger;

    public AuditLoggerDecorator(
            AuditLogger logger) {

        this.logger = logger;
    }
}
