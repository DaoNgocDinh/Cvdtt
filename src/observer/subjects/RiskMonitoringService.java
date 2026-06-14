/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer.subjects;

import model.risk.RuiRo;
import observer.Subject;
import observer.observers.AuditLogObserver;
import observer.observers.NotificationObserver;

/**
 *
 * @author FPTSHOP
 */
public class RiskMonitoringService extends Subject {

    private static final RiskMonitoringService INSTANCE = new RiskMonitoringService();

    private RiskMonitoringService() {
        registerObserver(new NotificationObserver());
        registerObserver(new AuditLogObserver());
    }

    public static RiskMonitoringService getInstance() {
        return INSTANCE;
    }

    public void publishRisk(RuiRo ruiRo) {
        if (ruiRo != null) {
            notifyObservers("risk.detected", ruiRo);
        }
    }
}
