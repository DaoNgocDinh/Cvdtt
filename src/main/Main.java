package main;

import strategy.loan.*;
import strategy.risk.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== LOAN MANAGEMENT =====");

        LoanManager loanManager = new LoanManager();

        // Vay cá nhân
        Loan personalLoan =
                new Loan("Personal Loan",
                        100000000);

        loanManager.setStrategy(
                new PersonalLoanStrategy()
        );

        loanManager.createLoan(
                personalLoan
        );

        System.out.println();

        // Vay mua nhà
        Loan homeLoan =
                new Loan("Home Loan",
                        500000000);

        loanManager.setStrategy(
                new HomeLoanStrategy()
        );

        loanManager.createLoan(
                homeLoan
        );

        System.out.println();

        // Vay doanh nghiệp
        Loan businessLoan =
                new Loan("Business Loan",
                        1000000000);

        loanManager.setStrategy(
                new BusinessLoanStrategy()
        );

        loanManager.createLoan(
                businessLoan
        );

        System.out.println("\n===== RISK MANAGEMENT =====");

        RiskManager riskManager =
                new RiskManager();

        // Rủi ro đăng nhập
        riskManager.setStrategy(
                new LoginRiskStrategy()
        );

        riskManager.analyzeRisk(
                new RiskEvent(
                        "admin",
                        "5 failed login attempts"
                )
        );

        System.out.println();

        // Rủi ro phân quyền
        riskManager.setStrategy(
                new PermissionRiskStrategy()
        );

        riskManager.analyzeRisk(
                new RiskEvent(
                        "employee01",
                        "Attempt to access Admin Dashboard"
                )
        );

        System.out.println();

        // Rủi ro dữ liệu
        riskManager.setStrategy(
                new DataRiskStrategy()
        );

        riskManager.analyzeRisk(
                new RiskEvent(
                        "manager01",
                        "Mass delete operation detected"
                )
        );
    }
}