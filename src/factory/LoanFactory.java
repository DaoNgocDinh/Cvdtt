/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import builder.LoanBuilder;
import model.loan.Loan;

/**
 *
 * @author FPTSHOP
 */
public class LoanFactory {

    public static Loan createPersonalLoan(String loanId, String customerId, double amount, int termMonths, double interestRate) {
        return LoanBuilder.builder()
                .setLoanId(loanId)
                .setCustomerId(customerId)
                .setAmount(amount)
                .setTermMonths(termMonths)
                .setInterestRate(interestRate)
                .setLoanType("Personal")
                .setStatus("Pending")
                .setStartDate(java.time.LocalDate.now())
                .setEndDate(java.time.LocalDate.now().plusMonths(termMonths))
                .setRiskLevel("Medium")
                .build();
    }
}
