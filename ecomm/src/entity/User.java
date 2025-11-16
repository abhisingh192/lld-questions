package entity;

public class User {

    private final String id;
    private final String username;
    private final String password;
    private String email;
    private String address;
    private double wallet;

    public User(String id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.wallet = 0.0;
    }

    public boolean checkPassword(String p) {
        return this.password.equals(p);
    }

    @Override
    public String toString() {
        return username + " (" + email + ")";
    }

    public String getId() {
        return id;
    }

    public double getWallet() {
        return wallet;
    }



    public void addToWallet(double amount) {
        this.wallet += amount;
    }

    public String getUsername() {
        return username;
    }



    public void setWallet(double wallet) {
        this.wallet = wallet;
    }


}
