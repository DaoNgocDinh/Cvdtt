package chainofresponsibility.risk;

public abstract class RiskHandler {

    protected RiskHandler next;

    public void setNext(RiskHandler next) {
        this.next = next;
    }

    protected void handleNext(RiskContext context) {

        if (next != null) {
            next.handle(context);
        }
    }

    public abstract void handle(RiskContext context);
}