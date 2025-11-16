import java.util.Scanner;

public class ATM {

    private CashDispenser cashDispenser;
    private BankServer bankServer;

    public ATM(CashDispenser cashDispenser, BankServer bankServer) {
        this.cashDispenser = cashDispenser;
        this.bankServer = bankServer;
    }

    public void start(Card card, String pin) {
        if (!bankServer.authenticate(card.getCardNumber(), pin)) {
            System.out.println("❌ Authentication failed!");
            return;
        }
        System.out.println("✅ Authentication successful!");
//        String accountNumber = bankServer.getAccountNumberFromCard(card.getCardNumber());

        // Menu Simulation
        System.out.println("Choose Option:");
        System.out.println("1. Balance Inquiry");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("📊 Balance: " + bankServer.getBalance(card.getCardNumber()));
                break;

            case 2:
                System.out.print("Enter amount: ");
                double withdrawAmt = sc.nextDouble();
                if (bankServer.withdrawByCard(card.getCardNumber(), withdrawAmt) && cashDispenser.dispenseCash(withdrawAmt)) {
                    System.out.println("✅ Withdrawal successful!");
                } else {
                    System.out.println("❌ Withdrawal failed!");
                }
                break;

            case 3:
                System.out.print("Enter amount: ");
                double depositAmt = sc.nextDouble();
                bankServer.depositByCard(card.getCardNumber(), depositAmt);
                System.out.println("✅ Deposit successful!");
                break;

            default:
                System.out.println("❌ Invalid option");
        }
    }
}
