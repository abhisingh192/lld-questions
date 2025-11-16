public class Main {
    public static void main(String[] args) {

        System.out.println("=== Water Bottle VENDING MACHINE ===");

        int itemCount = 2;
        int itemPrice = 20;

        VendingMachine vendingMachine = new VendingMachine(itemCount, itemPrice);

        vendingMachine.printStatus();
        System.out.println("1. Trying to select item without coin:");
        vendingMachine.selectItem();  // Should ask for coin, no state change
        vendingMachine.printStatus();

        System.out.println("2. Inserting coin:");
        vendingMachine.insertCoin(10);  // State changes to HAS_COIN
        vendingMachine.printStatus();

        System.out.println("3. Selecting item with insufficient funds:");
        vendingMachine.selectItem();  // Insufficient funds, stays in HAS_COIN
        vendingMachine.printStatus();

        System.out.println("4. Adding more coins:");
        vendingMachine.insertCoin(10);  // Add more money, stays in HAS_COIN
        vendingMachine.printStatus();

        System.out.println("5. Selecting item Now");
        vendingMachine.selectItem();  // State changes to SOLD
        vendingMachine.printStatus();

        System.out.println("6. Dispensing item:");
        vendingMachine.dispenseItem(); // State changes to NO_COIN (items remaining)
        vendingMachine.printStatus();

        System.out.println("7. Buying last item:");
        vendingMachine.insertCoin(20);  // State changes to HAS_COIN
        vendingMachine.selectItem();  // State changes to SOLD
        vendingMachine.dispenseItem(); // State changes to SOLD_OUT (no items left)
        vendingMachine.printStatus();

        System.out.println("8. Trying to use sold out vendingMachine:");
        vendingMachine.insertCoin(5);  // Coin returned, stays in SOLD_OUT

        System.out.println("9. Trying to use sold out vendingMachine:");
        vendingMachine.refillStock(2);
        vendingMachine.printStatus(); // State changes NO_COIN
    }
}