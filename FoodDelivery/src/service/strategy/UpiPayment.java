package service.strategy;

import entity.Customer;
import entity.PaymentStatus;

public class UpiPayment implements PaymentStrategy {
    @Override
    public PaymentStatus pay(Customer c, double amount) {
        // Simulate UPI payment processing
        System.out.println("Processing UPI payment of $" + amount + " for customer " + c.getName());
        // In a real implementation, integrate with a UPI payment gateway here
        return (c.getWallet() >= amount ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
    }
}
