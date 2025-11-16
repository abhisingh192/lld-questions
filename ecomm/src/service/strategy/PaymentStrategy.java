package service.strategy;

import entity.User;

public interface PaymentStrategy {

    boolean pay(User user, double amount);


}
