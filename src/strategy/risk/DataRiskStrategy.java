package strategy.risk;

public class DataRiskStrategy
        implements RiskStrategy {

    @Override
    public Alert evaluateRisk(
            RiskEvent event) {

        return new Alert(
                "Data Risk Detected: "
                        + event.getDescription()
        );
    }
}