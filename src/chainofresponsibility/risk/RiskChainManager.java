package chainofresponsibility.risk;

public class RiskChainManager {

    private RiskHandler chain;

    public RiskChainManager() {

        RiskHandler h1
                = new CreditRiskHandler();

        RiskHandler h2
                = new PaymentRiskHandler();

        RiskHandler h3
                = new DocumentRiskHandler();

        h1.setNext(h2);
        h2.setNext(h3);

        chain = h1;
    }

    public void evaluate(
            RiskContext context) {

        chain.handle(context);
    }
}
