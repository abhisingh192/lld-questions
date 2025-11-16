import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        SplitwiseService svc = new SplitwiseService();

        // Create users
        svc.createUser("u1", "Alice");
        svc.createUser("u2", "Bob");
        svc.createUser("u3", "Charlie");
        svc.createUser("u4", "Diana");

        // Create group
        svc.createGroup("g1", "Trip", Arrays.asList("u1","u2","u3"));

        // Add another user
        svc.addUserToGroup("g1", "u4");

        // 1) Equal split expense: Alice pays 120 split among u1,u2,u3,u4
        SplitStrategy equal = new EqualSplitStrategy();
        Expense e1 = svc.addExpense("g1", "u1", new BigDecimal("120.00"), "Lunch", Arrays.asList("u1","u2","u3","u4"), equal, null);
        System.out.println("Added expense: " + e1);

        // 2) Percentage split: Bob pays 100, shares are 50%,30%,20% among u1,u2,u3 (here participants include payer)
        SplitStrategy perc = new PercentSplitStrategy();
        Map<String,Object> extras1 = new HashMap<>();
        Map<String,BigDecimal> percentMap = new HashMap<>();
        percentMap.put("u1", new BigDecimal("50"));
        percentMap.put("u2", new BigDecimal("30"));
        percentMap.put("u3", new BigDecimal("20"));
        extras1.put("percentMap", percentMap);
        Expense e2 = svc.addExpense("g1", "u2", new BigDecimal("100.00"), "Taxi", Arrays.asList("u1","u2","u3"), perc, extras1);
        System.out.println("Added expense: " + e2);

        // 3) Exact split: Charlie pays 55, exact amounts provided
        SplitStrategy exact = new ExactSplitStrategy();
        Map<String,Object> extras2 = new HashMap<>();
        Map<String,BigDecimal> exactMap = new HashMap<>();
        exactMap.put("u1", new BigDecimal("20"));
        exactMap.put("u2", new BigDecimal("15"));
        exactMap.put("u3", new BigDecimal("20"));
        extras2.put("exactMap", exactMap);
        Expense e3 = svc.addExpense("g1", "u3", new BigDecimal("55.00"), "Snacks", Arrays.asList("u1","u2","u3"), exact, extras2);
        System.out.println("Added expense: " + e3);

        // Print balances for Alice
        svc.printUserBalances("u1");
        // Print balances for Bob
        svc.printUserBalances("u2");
        // Print group expenses
        svc.printGroupExpenses("g1");

        // Settle: u2 pays u1 some amount
        svc.settleUp("g1", "u2", "u1", new BigDecimal("10.00"));

        // Print balances after settle
        svc.printUserBalances("u1");
        svc.printUserBalances("u2");

        // Show concurrent safety demonstration (simple): two threads adding expenses concurrently
        Thread t1 = new Thread(() -> {
            svc.addExpense("g1", "u1", new BigDecimal("30.00"), "Coffee", Arrays.asList("u1","u2"), equal, null);
            System.out.println("Thread1 added coffee expense");
        });
        Thread t2 = new Thread(() -> {
            svc.addExpense("g1", "u2", new BigDecimal("40.00"), "Museum", Arrays.asList("u1","u2","u3"), equal, null);
            System.out.println("Thread2 added museum expense");
        });
        t1.start(); t2.start();
        try { t1.join(); t2.join(); } catch (InterruptedException ex){ Thread.currentThread().interrupt(); }

        svc.printGroupExpenses("g1");
        svc.printUserBalances("u1");
        svc.printUserBalances("u2");    }
}