package decorator.audit;

public class AlertDecorator
        extends AuditLoggerDecorator {

    public AlertDecorator(
            AuditLogger logger) {

        super(logger);
    }

    @Override
    public void log(String message) {

        logger.log(message);

        if (message.contains("deleted")) {

            System.out.println(
                    "[ALERT] Tai khoan vua bi xoa");
        }

        if (message.contains("failed")) {

            System.out.println(
                    "[ALERT] Dang nhap that bai");
        }

        if (message.contains("loan.created")) {

            System.out.println(
                    "[ALERT] Khoan vay moi duoc tao");
        }
    }
}
