/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import notification.Notification;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple in-memory repository for internal notifications.
 */
public class NotificationRepository {

    private static final List<Notification> notifications = new ArrayList<>();

    public void save(Notification notification) {
        if (notification != null) {
            synchronized (notifications) {
                notifications.add(notification);
            }
        }
    }

    public List<Notification> findAll() {
        synchronized (notifications) {
            return Collections.unmodifiableList(new ArrayList<>(notifications));
        }
    }
}
