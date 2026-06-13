package strategy.risk;

public class PermissionRiskStrategy
        implements RiskStrategy {

    @Override
    public Alert evaluateRisk(
            RiskEvent event) {

        return new Alert(
                "Permission Risk Detected: "
                        + event.getDescription()
        );
    }
}