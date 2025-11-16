package service.strategy;

import entity.Customer;
import entity.PaymentStatus;

public interface PaymentStrategy {

    PaymentStatus pay(Customer c, double amount);
}
