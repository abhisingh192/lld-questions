package service.strategy;

public class CardPayment implements PaymentStrategy {
    @Override
    public boolean pay(entity.User user, double amount) {
        // Assume card payment is always successful for this example
        return true;
    }


}
