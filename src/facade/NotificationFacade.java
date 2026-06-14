/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;

import notification.Notification;
import service.NotificationService;
import java.util.List;

/**
 * Facade for internal notification history.
 */
public class NotificationFacade {

    private final NotificationService notificationService;

    public NotificationFacade() {
        notificationService = new NotificationService();
    }

    public void sendNotification(Notification notification) {
        notificationService.saveNotification(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
}
