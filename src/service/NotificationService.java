/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import notification.Notification;
import repository.NotificationRepository;
import java.util.List;

/**
 * Service for internal notification history.
 */
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService() {
        this.repository = new NotificationRepository();
    }

    public void saveNotification(Notification notification) {
        repository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }
}
