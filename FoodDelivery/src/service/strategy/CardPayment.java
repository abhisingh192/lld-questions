package service.strategy;

import entity.Customer;
import entity.PaymentStatus;

public class CardPayment implements PaymentStrategy {
    @Override
    public PaymentStatus pay(Customer c, double amount) {
        // Simulate card payment processing
        System.out.println("Processing card payment of $" + amount + " for customer " + c.getName());
        // In a real implementation, integrate with a payment gateway here
        return PaymentStatus.SUCCESS;
    }
}
