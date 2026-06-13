/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import java.time.LocalDateTime;
import model.risk.RiskAlert;

/**
 *
 * @author FPTSHOP
 */
public class RiskAlertBuilder {

    private RiskAlert alert;

    private RiskAlertBuilder() {
        this.alert = new RiskAlert();
    }

    public static RiskAlertBuilder builder() {
        return new RiskAlertBuilder();
    }

    public RiskAlertBuilder setRiskId(String riskId) {
        alert.setRiskId(riskId);
        return this;
    }

    public RiskAlertBuilder setRelatedLoanId(String relatedLoanId) {
        alert.setRelatedLoanId(relatedLoanId);
        return this;
    }

    public RiskAlertBuilder setRelatedCustomerId(String relatedCustomerId) {
        alert.setRelatedCustomerId(relatedCustomerId);
        return this;
    }

    public RiskAlertBuilder setLevel(String level) {
        alert.setLevel(level);
        return this;
    }

    public RiskAlertBuilder setDescription(String description) {
        alert.setDescription(description);
        return this;
    }

    public RiskAlertBuilder setDetectedAt(LocalDateTime detectedAt) {
        alert.setDetectedAt(detectedAt);
        return this;
    }

    public RiskAlertBuilder setResolved(boolean resolved) {
        alert.setResolved(resolved);
        return this;
    }

    public RiskAlert build() {
        return alert;
    }
}
