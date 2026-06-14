package repository;

import notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {

    private static final List<Notification> notifications = new ArrayList<>();

    public void save(Notification notification) {
        if (notification != null) {
            notifications.add(notification);
        }
    }

    public List<Notification> findByRecipient(String recipient) {
        List<Notification> result = new ArrayList<>();
        if (recipient == null || recipient.isEmpty()) {
            return result;
        }
        for (Notification notification : notifications) {
            if (recipient.equals(notification.getRecipient())) {
                result.add(notification);
            }
        }
        return result;
    }

    public List<Notification> findAll() {
        return new ArrayList<>(notifications);
    }

    public void markAsRead(String notificationId) {
        if (notificationId == null || notificationId.isEmpty()) {
            return;
        }
        for (Notification notification : notifications) {
            if (notificationId.equals(notification.getId())) {
                notification.setStatus("Da doc");
                return;
            }
        }
    }
}
