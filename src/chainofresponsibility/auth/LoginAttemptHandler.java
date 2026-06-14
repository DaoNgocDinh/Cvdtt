/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility.auth;

import model.account.AuthRequest;

public class LoginAttemptHandler extends AuthHandler {

    private static final int MAX_ATTEMPT = 5;

    @Override
    public boolean handle(AuthRequest request) {

        if (request.account.getLocker()) {
            System.out.println("ACCOUNT IS LOCKED");
            return false;
        }

        return handleNext(request);
    }
}
