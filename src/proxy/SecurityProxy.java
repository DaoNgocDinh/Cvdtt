/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proxy;

import model.account.TaiKhoan;
import service.PermissionService;

/**
 *
 * @author FPTSHOP
 */
public class SecurityProxy {

    private final PermissionService permissionService;

    public SecurityProxy(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public boolean hasPermission(TaiKhoan taiKhoan, String requiredRole) {
        boolean grant = permissionService.hasPermission(taiKhoan, requiredRole);
        if (!grant) {
            System.out.println("SECURITY PROXY: Access denied to account '" + (taiKhoan != null ? taiKhoan.getMaTaiKhoan() : "unknown") + "' for role '" + requiredRole + "'.");
        }
        return grant;
    }
}
