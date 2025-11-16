public class SoldOutState implements VendingState{
    @Override
    public VendingState insertCoin(VendingMachine vendingMachine, int amount) {

        System.out.println("Item is sold out. Can't insert coin.");
        return vendingMachine.getSoldOutState();
    }

    @Override
    public VendingState selectItem(VendingMachine vendingMachine) {

        System.out.println("Item is sold out. Can't select item.");
        return vendingMachine.getSoldOutState();
    }

    @Override
    public VendingState dispenseItem(VendingMachine vendingMachine) {
        System.out.println("Item is sold out. Can't dispense item.");
        return vendingMachine.getSoldOutState();
    }

    @Override
    public VendingState returnCoin(VendingMachine vendingMachine) {
        System.out.println("Item is sold out. No coin inserted.");
        return vendingMachine.getSoldOutState();

    }

    @Override
    public VendingState refillStock(VendingMachine vendingMachine, int count) {

        vendingMachine.addItemCount(count);
        System.out.println("refilled item count: " + vendingMachine.getItemCount());
        return vendingMachine.getNoCoinState();
    }

    @Override
    public String getStateName() {
        return "SOLD_OUT";
    }
}
