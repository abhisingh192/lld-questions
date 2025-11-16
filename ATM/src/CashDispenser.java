public class CashDispenser {
    private double cashAvailable;

    public CashDispenser(int initialCash) {
        this.cashAvailable = initialCash;
    }

    public boolean dispenseCash(double amount) {
        if (amount <= cashAvailable) {
            cashAvailable -= amount;
            System.out.println("💸 Dispensed: " + amount);
            return true;
        }
        System.out.println("🚨 ATM out of cash!");
        return false;
    }

    public double getCashAvailable() {
        return cashAvailable;
    }
}
