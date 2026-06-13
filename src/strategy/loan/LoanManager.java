package strategy.loan;

public class LoanManager {

    private LoanStrategy strategy;

    public void setStrategy(
            LoanStrategy strategy) {

        this.strategy = strategy;
    }

    public void createLoan(
            Loan loan) {

        strategy.processLoan(loan);

        System.out.println(
                "Loan Created:"
        );

        System.out.println(
                loan
        );
    }
}