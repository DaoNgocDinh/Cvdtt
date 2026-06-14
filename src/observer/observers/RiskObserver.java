/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.observers;

import model.account.TaiKhoan;
import observer.Observer;

/**
 * Observer nhận thông báo rủi ro và hiển thị cảnh báo phù hợp.
 */
public class RiskObserver implements Observer {

    @Override
    public void update(String event, Object data) {
        if (!(data instanceof TaiKhoan)) {
            return;
        }

        TaiKhoan taiKhoan = (TaiKhoan) data;
        switch (event) {
            case "risk.high" -> System.out.println("RISK: Rủi ro cao, không thể xóa tài khoản '" + taiKhoan.getMaTaiKhoan() + "'.");
            case "risk.medium" -> System.out.println("RISK: Rủi ro trung bình, tài khoản '" + taiKhoan.getMaTaiKhoan() + "' đang hoạt động.");
            case "risk.low" -> System.out.println("RISK: Rủi ro thấp, tài khoản '" + taiKhoan.getMaTaiKhoan() + "' có thể xóa.");
            default -> {
                // Không xử lý các sự kiện khác
            }
        }
    }
}
