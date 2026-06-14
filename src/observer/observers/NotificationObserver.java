/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.observers;

import model.account.TaiKhoan;
import observer.Observer;

/**
 *
 * @author FPTSHOP
 */
public class NotificationObserver implements Observer {

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
            System.out.println("NOTIFICATION: Tai khoan '" + taiKhoan.getMaTaiKhoan() + "' da duoc cap nhat.");
        } else if ("taiKhoan.deleted".equals(event) && data instanceof TaiKhoan) {
            TaiKhoan taiKhoan = (TaiKhoan) data;
            System.out.println("NOTIFICATION: Tai khoan '" + taiKhoan.getMaTaiKhoan() + "' da duoc xoa.");
        }
    }
}
