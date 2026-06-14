package service;

import notification.Notification;
import repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService() {
        repository = new NotificationRepository();
    }

    public Notification sendNotification(String sender, String recipient, String title, String message) {
        Notification notification = new Notification(
                UUID.randomUUID().toString(),
                sender,
                recipient,
                title,
                message,
                LocalDateTime.now(),
                "Moi"
        );
        repository.save(notification);
        return notification;
    }

    public List<Notification> getNotificationsForUser(String recipient) {
        return repository.findByRecipient(recipient);
    }

    public List<Notification> getNotificationsForUser(String recipient, String roleName) {
        List<Notification> result = new ArrayList<>();
        for (Notification notification : repository.findAll()) {
            if (recipient != null && recipient.equals(notification.getRecipient())) {
                result.add(notification);
            } else if (roleName != null && roleName.equals(notification.getRecipient())) {
                result.add(notification);
            }
        }
        return result;
    }

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    public void markAsRead(String notificationId) {
        repository.markAsRead(notificationId);
    }
}
