package strategy.risk;

public class RiskManager {

    private RiskStrategy strategy;

    public void setStrategy(
            RiskStrategy strategy) {

        this.strategy = strategy;
    }

    public void analyzeRisk(
            RiskEvent event) {

        Alert alert =
                strategy.evaluateRisk(event);

        alert.showAlert();
    }
}