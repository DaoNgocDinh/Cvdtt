/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import model.account.TaiKhoan;

/**
 * Simple in-memory session holder for the current authenticated user.
 */
public class AuthSession {

    private static TaiKhoan currentUser;

    public static TaiKhoan getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(TaiKhoan taiKhoan) {
        currentUser = taiKhoan;
    }

    public static void clear() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
