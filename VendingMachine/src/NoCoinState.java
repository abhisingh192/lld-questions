public class NoCoinState implements VendingState{


    @Override
    public VendingState insertCoin(VendingMachine vendingMachine, int amount) {
        vendingMachine.setInsertedCoins(amount);
        System.out.println("inserted coin: Rs " + amount + ", total inserted: Rs " + vendingMachine.getInsertedCoins());
        return vendingMachine.getHasCoinState();
    }

    @Override
    public VendingState selectItem(VendingMachine vendingMachine) {
        System.out.println("please insert coin first");
        return vendingMachine.getNoCoinState();
    }

    @Override
    public VendingState dispenseItem(VendingMachine vendingMachine) {
        System.out.println("please insert coin and select item first");
        return vendingMachine.getNoCoinState();
    }

    @Override
    public VendingState returnCoin(VendingMachine vendingMachine) {
        System.out.println("please insert coin  first");
        return vendingMachine.getNoCoinState();     }

    @Override
    public VendingState refillStock(VendingMachine vendingMachine, int count) {
        vendingMachine.addItemCount(count);
        System.out.println("refilled item count: " + vendingMachine.getItemCount());
        return vendingMachine.getNoCoinState();
    }

    @Override
    public String getStateName() {
        return "NO_COIN";
    }
}
