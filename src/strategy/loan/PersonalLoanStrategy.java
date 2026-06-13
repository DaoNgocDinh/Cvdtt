package strategy.loan;

public class PersonalLoanStrategy
        implements LoanStrategy {

    @Override
    public void processLoan(
            Loan loan) {

        loan.setInterestRate(12);
    }
}