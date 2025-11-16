package service.strategy;

import entity.PaymentMethod;

public class PaymentFactory {

    public static PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case CASH:
                return new CodPayment();
            case UPI:
                return new UpiPayment();
            case CARD:
                return new CardPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + paymentMethod);
        }
    }
}
