/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.observers;

import model.account.TaiKhoan;
import model.risk.RuiRo;
import notification.Notification;
import notification.UpdateNotificationPayload;
import observer.Observer;
import facade.NotificationFacade;
import utils.AuthSession;

/**
 *
 * @author FPTSHOP
 */
public class NotificationObserver implements Observer {

    private final NotificationFacade notificationFacade;

    public NotificationObserver() {
        notificationFacade = new NotificationFacade();
    }

    @Override
    public void update(String event, Object data) {
        if ("login.success".equals(event) && data instanceof TaiKhoan) {
            TaiKhoan taiKhoan = (TaiKhoan) data;
            System.out.println("NOTIFICATION: Welcome " + taiKhoan.getHoTen() + "! Login successful.");
        } else if ("login.failure".equals(event)) {
            System.out.println("NOTIFICATION: Please check your username and password.");
        } else if ("taiKhoan.created".equals(event) && data instanceof TaiKhoan) {
            TaiKhoan taiKhoan = (TaiKhoan) data;
            System.out.println("NOTIFICATION: Tai khoan '" + taiKhoan.getMaTaiKhoan() + "' da duoc tao.");
            saveInternalNotification("Tao tai khoan", "Tai khoan '" + taiKhoan.getMaTaiKhoan() + "' duoc tao.", "Nhan vien");
        } else if ("taiKhoan.updated".equals(event) && data instanceof UpdateNotificationPayload) {
            UpdateNotificationPayload payload = (UpdateNotificationPayload) data;
            TaiKhoan updated = payload.getAfter();
            System.out.println("NOTIFICATION: Tai khoan '" + updated.getMaTaiKhoan() + "' da duoc cap nhat.");
            saveInternalNotification(
                    "Cap nhat tai khoan",
                    payload.getChangeDescription(),
                    "Nhan vien"
            );
        } else if ("taiKhoan.deleted".equals(event) && data instanceof TaiKhoan) {
            TaiKhoan taiKhoan = (TaiKhoan) data;
            System.out.println("NOTIFICATION: Tai khoan '" + taiKhoan.getMaTaiKhoan() + "' da duoc xoa.");
            saveInternalNotification("Xoa tai khoan", "Tai khoan '" + taiKhoan.getMaTaiKhoan() + "' da bi xoa.", "Nhan vien");
        } else if ("risk.detected".equals(event) && data instanceof RuiRo) {
            RuiRo ruiRo = (RuiRo) data;
            System.out.println("NOTIFICATION: Phat hien rui ro moi [" + ruiRo.getMaRuiRo() + "] "
                    + ruiRo.getTenRuiRo() + " cho ma khoan vay " + ruiRo.getMaKhoanVay() + ".");
            saveInternalNotification(
                    "Canh bao rui ro",
                    "Phat hien rui ro moi [" + ruiRo.getMaRuiRo() + "] " + ruiRo.getTenRuiRo() + " cho ma khoan vay " + ruiRo.getMaKhoanVay() + ".",
                    "Risk Officer"
            );
        }
    }

    private void saveInternalNotification(String title, String content, String receiver) {
        Notification notification = new Notification();
        notification.setSender(AuthSession.isLoggedIn() ? AuthSession.getCurrentUser().getHoTen() : "System");
        notification.setReceiver(receiver);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setStatus("Moi");
        notificationFacade.sendNotification(notification);
    }
}
