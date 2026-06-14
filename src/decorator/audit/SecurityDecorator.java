package decorator.audit;

public class SecurityDecorator
        extends AuditLoggerDecorator {

    public SecurityDecorator(
            AuditLogger logger) {

        super(logger);
    }

    @Override
    public void log(String message) {

        logger.log(message);

        System.out.println(
                "[SECURITY] Da kiem tra bao mat");
    }
}
