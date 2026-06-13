/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import java.time.LocalDate;
import model.loan.Loan;

/**
 *
 * @author FPTSHOP
 */
public class LoanBuilder {

    private String loanId;
    private String customerId;
    private double amount;
    private int termMonths;
    private double interestRate;
    private String loanType;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String riskLevel;

    private LoanBuilder() {
    }

    public static LoanBuilder builder() {
        return new LoanBuilder();
    }

    public LoanBuilder loanId(String loanId) {
        this.loanId = loanId;
        return this;
    }

    public LoanBuilder customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public LoanBuilder amount(double amount) {
        this.amount = amount;
        return this;
    }

    public LoanBuilder termMonths(int termMonths) {
        this.termMonths = termMonths;
        return this;
    }

    public LoanBuilder interestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public LoanBuilder loanType(String loanType) {
        this.loanType = loanType;
        return this;
    }

    public LoanBuilder status(String status) {
        this.status = status;
        return this;
    }

    public LoanBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LoanBuilder endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public LoanBuilder riskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }

    public Loan build() {
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        loan.setCustomerId(customerId);
        loan.setAmount(amount);
        loan.setTermMonths(termMonths);
        loan.setInterestRate(interestRate);
        loan.setLoanType(loanType);
        loan.setStatus(status);
        loan.setStartDate(startDate);
        loan.setEndDate(endDate);
        loan.setRiskLevel(riskLevel);
        return loan;
    }
}
