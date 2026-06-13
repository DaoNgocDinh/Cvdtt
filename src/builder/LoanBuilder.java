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

    private Loan loan;

    private LoanBuilder() {
        this.loan = new Loan();
    }

    public static LoanBuilder builder() {
        return new LoanBuilder();
    }

    public LoanBuilder setLoanId(String loanId) {
        loan.setLoanId(loanId);
        return this;
    }

    public LoanBuilder setCustomerId(String customerId) {
        loan.setCustomerId(customerId);
        return this;
    }

    public LoanBuilder setAmount(double amount) {
        loan.setAmount(amount);
        return this;
    }

    public LoanBuilder setTermMonths(int termMonths) {
        loan.setTermMonths(termMonths);
        return this;
    }

    public LoanBuilder setInterestRate(double interestRate) {
        loan.setInterestRate(interestRate);
        return this;
    }

    public LoanBuilder setLoanType(String loanType) {
        loan.setLoanType(loanType);
        return this;
    }

    public LoanBuilder setStatus(String status) {
        loan.setStatus(status);
        return this;
    }

    public LoanBuilder setStartDate(LocalDate startDate) {
        loan.setStartDate(startDate);
        return this;
    }

    public LoanBuilder setEndDate(LocalDate endDate) {
        loan.setEndDate(endDate);
        return this;
    }

    public LoanBuilder setRiskLevel(String riskLevel) {
        loan.setRiskLevel(riskLevel);
        return this;
    }

    public Loan build() {
        return loan;
    }
}
