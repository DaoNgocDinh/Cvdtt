package strategy.risk;

public class Alert {

    private String message;

    public Alert(String message) {
        this.message = message;
    }

    public void showAlert() {
        System.out.println("ALERT: " + message);
    }
}