package service;

import entity.Driver;
import entity.Passenger;

public class PaymentService {

    public boolean chargePassenger(Passenger p, double amount) {
        if (p.getWalletBalance() >= amount) {
            p.setWalletBalance(p.getWalletBalance() - amount);
            return true;
        }
        return false;
    }

    public void payDriver(Driver d, double amount) {
        d.addEarnings(amount);
    }

}
