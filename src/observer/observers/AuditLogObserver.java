/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.observers;

import model.account.TaiKhoan;
import observer.Observer;
import decorator.audit.*;

/**
 *
 * @author FPTSHOP
 */
public class AuditLogObserver implements Observer {

    private final AuditLogger logger;

    public AuditLogObserver() {

        logger
                = new AlertDecorator(
                        new SecurityDecorator(
                                new BasicAuditLogger()));
    }

    @Override
    public void update(String event, Object data) {
        logger.log(event);
    }
}
