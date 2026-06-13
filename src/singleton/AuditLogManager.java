package singleton;

import java.util.ArrayList;
import java.util.List;

public class AuditLogManager {

    // Singleton Instance
    private static AuditLogManager instance;

    // Danh sách log
    private List<AuditLog> logs;

    // Constructor private
    private AuditLogManager() {
        logs = new ArrayList<>();
    }

    // Lấy instance duy nhất
    public static AuditLogManager getInstance() {

        if (instance == null) {
            instance = new AuditLogManager();
        }

        return instance;
    }

    // Ghi log
    public void writeLog(String username, String action) {

        AuditLog log = new AuditLog(username, action);

        logs.add(log);

        System.out.println("Log saved: " + log);
    }

    // Hiển thị toàn bộ log
    public void showLogs() {

        System.out.println("\n===== AUDIT LOG =====");

        if (logs.isEmpty()) {
            System.out.println("No logs found.");
            return;
        }

        for (AuditLog log : logs) {
            System.out.println(log);
        }
    }

    // Trả về danh sách log
    public List<AuditLog> getLogs() {
        return logs;
    }
}