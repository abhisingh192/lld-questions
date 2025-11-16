public class DispenseState implements VendingState{
    @Override
    public VendingState insertCoin(VendingMachine vendingMachine, int amount) {
        System.out.println("please wait, dispensing item");
        return vendingMachine.getDispenseState();
    }

    @Override
    public VendingState selectItem(VendingMachine vendingMachine) {
        System.out.println("please wait, dispensing item");
        return vendingMachine.getDispenseState();    }

    @Override
    public VendingState dispenseItem(VendingMachine vendingMachine) {
        if (vendingMachine.getItemCount() > 0) {
            vendingMachine.releaseItem();
            System.out.println("Item dispensed. Items remaining: " + vendingMachine.getItemCount());
            if (vendingMachine.getItemCount() == 0) {
                System.out.println("Out of stock!");
                return vendingMachine.getSoldOutState();
            } else {
                return vendingMachine.getNoCoinState();
            }
        } else {
            System.out.println("No item to dispense");
            return vendingMachine.getSoldOutState();
        }
    }

    @Override
    public VendingState returnCoin(VendingMachine vendingMachine) {
        System.out.println("Can't return coin, dispensing in progress");
        return vendingMachine.getDispenseState();
    }

    @Override
    public VendingState refillStock(VendingMachine vendingMachine, int count) {
        System.out.println("Can't refil in this state");
        return vendingMachine.getDispenseState();
    }

    @Override
    public String getStateName() {
        return "DISPENSE";
    }
}
