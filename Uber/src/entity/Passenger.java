package entity;

public class Passenger {

    private String id;
    private String name;
    private double walletBalance;

    public Passenger(String id, String name, double walletBalance) {
        this.id = id;
        this.name = name;
        this.walletBalance = walletBalance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

}
