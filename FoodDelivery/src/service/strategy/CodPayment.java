package service.strategy;

import entity.Customer;
import entity.PaymentStatus;

public class CodPayment implements PaymentStrategy {
    @Override
    public PaymentStatus pay(Customer c, double amount) {
        // Simulate Cash on Delivery payment processing
        System.out.println("Processing Cash on Delivery payment of $" + amount + " for customer " + c.getName());
        // In a real implementation, handle COD specifics here
        return PaymentStatus.SUCCESS;
    }
}
