public interface VendingState {

    // these are the functions that each state must implement
    VendingState insertCoin(VendingMachine vendingMachine, int amount);
    VendingState selectItem(VendingMachine vendingMachine);

    VendingState dispenseItem(VendingMachine vendingMachine);

    VendingState returnCoin(VendingMachine vendingMachine);

    VendingState refillStock(VendingMachine vendingMachine, int count);

    String getStateName();
}
