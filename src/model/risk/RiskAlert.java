/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.risk;

import java.time.LocalDateTime;

/**
 *
 * @author FPTSHOP
 */
public class RiskAlert {

    private String riskId;
    private String relatedLoanId;
    private String relatedCustomerId;
    private String level;
    private String description;
    private LocalDateTime detectedAt;
    private boolean resolved;

    public RiskAlert() {
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getRelatedLoanId() {
        return relatedLoanId;
    }

    public void setRelatedLoanId(String relatedLoanId) {
        this.relatedLoanId = relatedLoanId;
    }

    public String getRelatedCustomerId() {
        return relatedCustomerId;
    }

    public void setRelatedCustomerId(String relatedCustomerId) {
        this.relatedCustomerId = relatedCustomerId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDetectedAt() {
        return detectedAt;
    }

    public void setDetectedAt(LocalDateTime detectedAt) {
        this.detectedAt = detectedAt;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
