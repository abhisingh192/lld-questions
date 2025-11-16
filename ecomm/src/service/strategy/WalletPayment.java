package service.strategy;

import entity.User;

/**
 * Deduct from user's wallet (synchronized on user).
 */
public class WalletPayment implements PaymentStrategy {
    @Override
    public boolean pay(User user, double amount) {
        synchronized (user) {
            if (user.getWallet() < amount) return false;
            user.setWallet( user.getWallet() - amount);
            return true;
        }
    }
}
