package strategy.risk;

public class RiskEvent {

    private String username;
    private String description;

    public RiskEvent(String username, String description) {
        this.username = username;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }
}