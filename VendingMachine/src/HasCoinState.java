public class HasCoinState implements VendingState{
    @Override
    public VendingState insertCoin(VendingMachine vendingMachine, int amount) {
        vendingMachine.addInsertedCoins(amount);
        System.out.println("inserted coin: Rs " + amount + ", total inserted: Rs " + vendingMachine.getInsertedCoins());
        return vendingMachine.getHasCoinState();
    }

    @Override
    public VendingState selectItem(VendingMachine vendingMachine) {
        if (vendingMachine.getInsertedCoins() >= vendingMachine.getItemPrice()) {
            System.out.println("Item selected. Dispensing...");

            int change = vendingMachine.getInsertedCoins() - vendingMachine.getItemPrice();
            if (change > 0) {
                System.out.println("Change returned: Rs " + change);
            }
            vendingMachine.setInsertedCoins(0);

            return vendingMachine.getDispenseState(); // Transition to DispenseState
        }
        else {
            int needed = vendingMachine.getItemPrice() - vendingMachine.getInsertedCoins();
            System.out.println("Insufficient funds. Need Rs " + needed + " more.");
            return vendingMachine.getHasCoinState(); // Stay in same state
        }
    }

    @Override
    public VendingState dispenseItem(VendingMachine vendingMachine) {
        System.out.println("please select item first");
        return vendingMachine.getHasCoinState();
    }

    @Override
    public VendingState returnCoin(VendingMachine vendingMachine) {
        System.out.println("Coin returned: Rs " + vendingMachine.getInsertedCoins());
        vendingMachine.setInsertedCoins(0);
        return vendingMachine.getNoCoinState();    }

    @Override
    public VendingState refillStock(VendingMachine vendingMachine, int count) {
        System.out.println("Can't refil in this state");
        return vendingMachine.getHasCoinState();
    }

    @Override
    public String getStateName() {
        return "HAS_COIN";
    }
}
