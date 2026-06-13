/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.account.TaiKhoan;

/**
 *
 * @author FPTSHOP
 */
public class PermissionService {

    public boolean hasPermission(TaiKhoan taiKhoan, String requiredRole) {
        if (taiKhoan == null || requiredRole == null) {
            return false;
        }

        if ("ADMIN".equalsIgnoreCase(taiKhoan.getRoleName())) {
            return true;
        }

        return requiredRole.equalsIgnoreCase(taiKhoan.getRoleName());
    }
}
