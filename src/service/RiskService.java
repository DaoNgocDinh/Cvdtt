/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.account.TaiKhoan;
import model.risk.RiskLevel;

/**
 *
 * @author FPTSHOP
 */
public class RiskService {
    public RiskLevel evaluateDeleteRisk(
            TaiKhoan taiKhoan) {

        if (taiKhoan == null) {
            return RiskLevel.HIGH;
        }

        // Không cho xóa Admin
        if ("ADMIN".equals(
                taiKhoan.getRoleName())) {

            return RiskLevel.HIGH;
        }

        // Nhân viên đang hoạt động
        if (!taiKhoan.getLocker()) {

            return RiskLevel.MEDIUM;
        }

        return RiskLevel.LOW;
    }
}
