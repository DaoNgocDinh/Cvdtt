package singleton;

public class SecurityService {

    private AuditLogManager logManager;

    public SecurityService() {
        logManager = AuditLogManager.getInstance();
    }

    // Đăng nhập
    public void login(String username) {

        System.out.println(username + " logged in.");

        logManager.writeLog(
                username,
                "Login system"
        );
    }

    // Đăng xuất
    public void logout(String username) {

        System.out.println(username + " logged out.");

        logManager.writeLog(
                username,
                "Logout system"
        );
    }

    // Truy cập chức năng
    public void accessFeature(
            String username,
            String featureName
    ) {

        System.out.println(
                username + " accessed: " + featureName
        );

        logManager.writeLog(
                username,
                "Access feature: " + featureName
        );
    }
}