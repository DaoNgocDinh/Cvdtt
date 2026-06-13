package strategy.risk;

public class LoginRiskStrategy
        implements RiskStrategy {

    @Override
    public Alert evaluateRisk(
            RiskEvent event) {

        return new Alert(
                "Login Risk Detected: "
                        + event.getDescription()
        );
    }
}