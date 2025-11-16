package service.strategy;

public class PaymentFactory {
    public static PaymentStrategy getPaymentStrategy(PaymentMethod method) {
        switch (method) {
            case CARD:
            case UPI:
                return new CardPayment();
            case WALLET:
                return new WalletPayment();
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }
}
