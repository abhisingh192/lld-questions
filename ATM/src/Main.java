public class Main {
    public static void main(String[] args) {
        BankServer bank = new BankServer();
        CashDispenser cashDispenser = new CashDispenser(10000);


        ATM atm = new ATM(cashDispenser, bank);

        // Create some accounts and cards
        Account account1 = new Account("123456", 1000);
        Card card1 = new Card("1111-2222-3333-4444", "1234");
        bank.addAccount(account1, card1);

        Account account2 = new Account("654321", 500);
        Card card2 = new Card("5555-6666-7777-8888", "5678");
        bank.addAccount(account2, card2);


        atm.start(card1, "123"); // Successful authentication
    }
}