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
public class SecurityObserver implements Observer {

    @Override
    public void update(String event, Object data) {
        if ("login.success".equals(event) && data instanceof TaiKhoan) {
            TaiKhoan taiKhoan = (TaiKhoan) data;
            if (Boolean.TRUE.equals(taiKhoan.getLocker())) {
                System.out.println("SECURITY: Locked account attempted to log in: " + taiKhoan.getMaTaiKhoan());
            }
        } else if ("login.failure".equals(event)) {
            System.out.println("SECURITY: Login failure detected for " + data);
        }
    }
}
