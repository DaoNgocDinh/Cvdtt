package singleton;

import java.util.ArrayList;
import java.util.List;

public class AuditLogManager {

    private static AuditLogManager instance;

    private List<AuditLog> logs;

    private AuditLogManager() {
        logs = new ArrayList<>();
        System.out.println("[SINGLETON] AuditLogManager constructor called.");
    }

    public static AuditLogManager getInstance() {
        System.out.println("[SINGLETON] AuditLogManager.getInstance() called.");

        if (instance == null) {
            System.out.println("[SINGLETON] Creating only one AuditLogManager instance.");
            instance = new AuditLogManager();
        } else {
            System.out.println("[SINGLETON] Reusing existing AuditLogManager instance.");
        }

        return instance;
    }

    public void writeLog(String maTaiKhoan, String hanhDong) {
        System.out.println("[SINGLETON] AuditLogManager.writeLog() is running.");

        AuditLog log = new AuditLog(maTaiKhoan, hanhDong);
        logs.add(log);

        System.out.println("[SINGLETON] Log saved: " + log);
    }

    public void showLogs() {
        System.out.println("\n===== AUDIT LOG RUNTIME =====");

        if (logs.isEmpty()) {
            System.out.println("[SINGLETON] No logs found.");
            return;
        }

        for (AuditLog log : logs) {
            System.out.println(log);
        }
    }

    public List<AuditLog> getLogs() {
        return logs;
    }
}