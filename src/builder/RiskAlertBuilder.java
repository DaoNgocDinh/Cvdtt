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

    private String riskId;
    private String relatedLoanId;
    private String relatedCustomerId;
    private String level;
    private String description;
    private LocalDateTime detectedAt;
    private boolean resolved;

    private RiskAlertBuilder() {
    }

    public static RiskAlertBuilder builder() {
        return new RiskAlertBuilder();
    }

    public RiskAlertBuilder riskId(String riskId) {
        this.riskId = riskId;
        return this;
    }

    public RiskAlertBuilder relatedLoanId(String relatedLoanId) {
        this.relatedLoanId = relatedLoanId;
        return this;
    }

    public RiskAlertBuilder relatedCustomerId(String relatedCustomerId) {
        this.relatedCustomerId = relatedCustomerId;
        return this;
    }

    public RiskAlertBuilder level(String level) {
        this.level = level;
        return this;
    }

    public RiskAlertBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RiskAlertBuilder detectedAt(LocalDateTime detectedAt) {
        this.detectedAt = detectedAt;
        return this;
    }

    public RiskAlertBuilder resolved(boolean resolved) {
        this.resolved = resolved;
        return this;
    }

    public RiskAlert build() {
        RiskAlert alert = new RiskAlert();
        alert.setRiskId(riskId);
        alert.setRelatedLoanId(relatedLoanId);
        alert.setRelatedCustomerId(relatedCustomerId);
        alert.setLevel(level);
        alert.setDescription(description);
        alert.setDetectedAt(detectedAt);
        alert.setResolved(resolved);
        return alert;
    }
}
