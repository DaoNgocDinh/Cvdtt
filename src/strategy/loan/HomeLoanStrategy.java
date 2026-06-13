package strategy.loan;

public class HomeLoanStrategy
        implements LoanStrategy {

    @Override
    public void processLoan(
            Loan loan) {

        loan.setInterestRate(8);
    }
}