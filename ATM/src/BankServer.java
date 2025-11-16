import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankServer {

    private Map<String, Account> accounts = new ConcurrentHashMap<>();
    private Map<String, Card> cards = new ConcurrentHashMap<>();
    private Map<String, String> cardToAccount = new ConcurrentHashMap<>(); // mapping card -> account

    public void addAccount(Account account, Card card) {
        accounts.put(account.getAccountNumber(), account);
        cards.put(card.getCardNumber(), card);
        cardToAccount.put(card.getCardNumber(), account.getAccountNumber());
    }

    public boolean authenticate(String cardNumber, String pin) {
        Card card = cards.get(cardNumber);
        return card != null && card.getPin().equals(pin);
    }

    public double getBalance(String cardNumber) {
        String accountNumber = cardToAccount.get(cardNumber);
        Account account = accounts.get(accountNumber);
        return account != null ? account.getBalance() : 0.0;
    }

    public double getBalanceByAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        return account != null ? account.getBalance() : 0.0;
    }

    public boolean withdrawByCard(String cardNumber, double amount) {
        String accountNumber = cardToAccount.get(cardNumber);
        Account account = accounts.get(accountNumber);
        return account != null && account.withdraw(amount);
    }

    public boolean withdrawByAccount(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        return account != null && account.withdraw(amount);
    }

    public boolean depositByCard(String cardNumber, double amount) {
        String accountNumber = cardToAccount.get(cardNumber);
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean depositByAccount(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }



}
