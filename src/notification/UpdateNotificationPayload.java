/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notification;

import model.account.TaiKhoan;

/**
 * Payload object used to create an update history notification.
 */
public class UpdateNotificationPayload {

    private final TaiKhoan updater;
    private final TaiKhoan before;
    private final TaiKhoan after;
    private final String changeDescription;

    public UpdateNotificationPayload(TaiKhoan updater, TaiKhoan before, TaiKhoan after, String changeDescription) {
        this.updater = updater;
        this.before = before;
        this.after = after;
        this.changeDescription = changeDescription;
    }

    public TaiKhoan getUpdater() {
        return updater;
    }

    public TaiKhoan getBefore() {
        return before;
    }

    public TaiKhoan getAfter() {
        return after;
    }

    public String getChangeDescription() {
        return changeDescription;
    }
}
