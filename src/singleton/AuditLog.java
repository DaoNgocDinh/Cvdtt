package singleton;

import java.time.LocalDateTime;

public class AuditLog {
    private String username;
    private String action;
    private LocalDateTime timestamp;

    public AuditLog(String username, String action) {
        this.username = username;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] "
                + username + " : "
                + action;
    }
}