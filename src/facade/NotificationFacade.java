package facade;

import notification.Notification;
import service.NotificationService;

import java.util.List;

public class NotificationFacade {

    private final NotificationService service;

    public NotificationFacade() {
        service = new NotificationService();
    }

    public void sendNotification(String sender, String recipient, String title, String message) {
        service.sendNotification(sender, recipient, title, message);
    }

    public List<Notification> getNotificationsForUser(String recipient) {
        return service.getNotificationsForUser(recipient);
    }

    public List<Notification> getNotificationsForUser(String recipient, String roleName) {
        return service.getNotificationsForUser(recipient, roleName);
    }

    public List<Notification> getAllNotifications() {
        return service.getAllNotifications();
    }

    public void markAsRead(String notificationId) {
        service.markAsRead(notificationId);
    }
}
