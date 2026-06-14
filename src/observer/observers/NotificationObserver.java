/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.observers;

import facade.NotificationFacade;
import model.account.TaiKhoan;
import observer.Observer;
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
        } else if ("taiKhoan.updated".equals(event) && data instanceof TaiKhoan) {
            TaiKhoan taiKhoan = (TaiKhoan) data;
            sendUpdateNotifications(taiKhoan);
            System.out.println("NOTIFICATION: Tai khoan '" + taiKhoan.getMaTaiKhoan() + "' da duoc cap nhat.");
        } else if ("taiKhoan.deleted".equals(event) && data instanceof TaiKhoan) {
            TaiKhoan taiKhoan = (TaiKhoan) data;
            System.out.println("NOTIFICATION: Tai khoan '" + taiKhoan.getMaTaiKhoan() + "' da duoc xoa.");
        }
    }

    private void sendUpdateNotifications(TaiKhoan taiKhoan) {
        TaiKhoan sender = AuthSession.getCurrentUser();
        String senderName = sender != null ? sender.getHoTen() : "System";
        String recipient = taiKhoan.getMaTaiKhoan();
        String title = "Thong bao cap nhat thong tin tai khoan";
        String message = "Admin " + senderName + " da cap nhat thong tin tai khoan cua ban.";
        notificationFacade.sendNotification(senderName, recipient, title, message);

        if (sender != null && !sender.getMaTaiKhoan().equals(recipient)) {
            String adminTitle = "Xac nhan thong bao da gui";
            String adminMessage = "Ban da cap nhat thong tin tai khoan '" + taiKhoan.getMaTaiKhoan() + "'.";
            notificationFacade.sendNotification(senderName, sender.getMaTaiKhoan(), adminTitle, adminMessage);
        }
    }
}
