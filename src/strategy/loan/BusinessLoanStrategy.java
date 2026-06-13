package strategy.loan;

public class BusinessLoanStrategy
        implements LoanStrategy {

    @Override
    public void processLoan(
            Loan loan) {

        loan.setInterestRate(10);
    }
}