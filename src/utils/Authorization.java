/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import model.account.TaiKhoan;
import proxy.SecurityProxy;
import service.PermissionService;

import javax.swing.*;

/**
 * Centralized helper for role-based access checks.
 */
public class Authorization {

    private static final SecurityProxy securityProxy = new SecurityProxy(new PermissionService());

    public static boolean requireRole(JFrame parent, String requiredRole) {
        TaiKhoan user = AuthSession.getCurrentUser();
        if (user == null) {
            JOptionPane.showMessageDialog(parent, "Bạn phải đăng nhập trước.", "Chưa đăng nhập", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean allowed = securityProxy.hasPermission(user, requiredRole);
        if (!allowed) {
            JOptionPane.showMessageDialog(parent, "Bạn không có quyền truy cập chức năng này.", "Không đủ quyền", JOptionPane.WARNING_MESSAGE);
        }
        return allowed;
    }

    public static boolean requireAnyRole(JFrame parent, String... roles) {
        TaiKhoan user = AuthSession.getCurrentUser();
        if (user == null) {
            JOptionPane.showMessageDialog(parent, "Bạn phải đăng nhập trước.", "Chưa đăng nhập", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        for (String role : roles) {
            if (securityProxy.hasPermission(user, role)) {
                return true;
            }
        }

        JOptionPane.showMessageDialog(parent, "Bạn không có quyền truy cập chức năng này.", "Không đủ quyền", JOptionPane.WARNING_MESSAGE);
        return false;
    }
}
