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
                .loanId(loanId)
                .customerId(customerId)
                .amount(amount)
                .termMonths(termMonths)
                .interestRate(interestRate)
                .loanType("Personal")
                .status("Pending")
                .startDate(java.time.LocalDate.now())
                .endDate(java.time.LocalDate.now().plusMonths(termMonths))
                .riskLevel("Medium")
                .build();
    }
}
