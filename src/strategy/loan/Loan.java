package strategy.loan;

public class Loan {

    private String type;
    private double amount;
    private double interestRate;

    public Loan(
            String type,
            double amount) {

        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void setInterestRate(
            double interestRate) {

        this.interestRate = interestRate;
    }

    @Override
    public String toString() {

        return "Loan{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", interestRate=" +
                interestRate +
                "%}";
    }
}