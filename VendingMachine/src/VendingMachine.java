public class VendingMachine {

    private VendingState currentState;

    private int itemCount;

    private int itemPrice;

    private int insertedCoins;

    private VendingState  noCoinState;

    private VendingState  hasCoinState;

    private VendingState  dispenseState;

    private VendingState  soldOutState;

    public VendingMachine (int itemCount, int itemPrice) {
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.insertedCoins = 0;

        this.noCoinState = new NoCoinState();
        this.hasCoinState = new HasCoinState();
        this.dispenseState = new DispenseState();
        this.soldOutState = new SoldOutState();

        if (itemCount > 0) {
            currentState = noCoinState;
        } else {
            currentState = soldOutState;
        }
    }

    public void insertCoin(int coin) {
        currentState = currentState.insertCoin(this, coin);
    }

    public void selectItem() {
        currentState = currentState.selectItem(this);
    }

    public void dispenseItem() {
        currentState = currentState.dispenseItem(this);
    }

    public void returnCoin() {
        currentState = currentState.returnCoin(this);
    }

    public void refillStock(int count) {
        currentState = currentState.refillStock(this, count);
    }

    // Print the status of Vending Machine
    public void printStatus() {
        System.out.println("\n--- Vending Machine Status ---");
        System.out.println("Items remaining: " + itemCount);
        System.out.println("Inserted coin: Rs " + insertedCoins);
        System.out.println("Current state: " + currentState.getStateName() + "\n");
    }

    // Getters and Setters

    public VendingState getNoCoinState() {
        return noCoinState;
    }
    public VendingState getHasCoinState() {
        return hasCoinState;
    }
    public VendingState getDispenseState() {
        return dispenseState;
    }
    public VendingState getSoldOutState() {
        return soldOutState;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getInsertedCoins() {
        return insertedCoins;
    }

    public void setInsertedCoins(int insertedCoins) {
        this.insertedCoins = insertedCoins;
    }

    public void addItemCount(int count) {
        this.itemCount += count;
    }

    public void addInsertedCoins(int amount) {
        this.insertedCoins += amount;
    }

    public void releaseItem() {
        if (itemCount > 0) {
            itemCount--;
        }
    }
}
