package strategy.risk;

public interface RiskStrategy {

    Alert evaluateRisk(RiskEvent event);

}