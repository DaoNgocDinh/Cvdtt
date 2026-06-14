/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility.auth;

import model.account.AuthRequest;

public class LoginAttemptHandler extends AuthHandler {

    @Override
    public boolean handle(AuthRequest request) {
        if (request.account.getLocker()) {
            System.out.println("[CoR]Tai khoan da bi khoa");
            return false;
        }
        return handleNext(request);
    }
}
