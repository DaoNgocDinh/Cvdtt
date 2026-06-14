/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chainofresponsibility.auth;

import model.account.AuthRequest;

public abstract class AuthHandler {

    protected AuthHandler next;

    public void setNext(AuthHandler next) {
        this.next = next;
    }

    public abstract boolean handle(AuthRequest request);

    protected boolean handleNext(AuthRequest request) {
        if (next == null) {
            return true;
        }
        return next.handle(request);
    }
}
