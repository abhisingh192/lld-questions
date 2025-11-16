import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SplitwiseService {

    final Map<String, User> users = new ConcurrentHashMap<>();
    final Map<String, Group> groups = new ConcurrentHashMap<>();

    // create user
    public User createUser(String id, String name){
        User u = new User(id, name);
        users.put(id, u);
        return u;
    }

    public Group createGroup(String id, String name, List<String> initialMembers){
        Group g = new Group(id, name);
        groups.put(id, g);
        for (String m : initialMembers) g.addMember(m);
        return g;
    }

    public void addUserToGroup(String groupId, String userId){
        Group g = groups.get(groupId);
        if (g == null) throw new NoSuchElementException("Group not found");
        g.addMember(userId);
    }

    // add expense (thread-safe per-group)
    public Expense addExpense(String groupId, String payerId, BigDecimal amount, String description,
                              List<String> participants, SplitStrategy strat, Map<String,Object> extras) {
        Group g = groups.get(groupId);
        if (g == null) throw new NoSuchElementException("Group not found");
        // ensure participants exist in group
        for (String p : participants) if (!g.members.contains(p)) throw new IllegalArgumentException("participant " + p + " not in group");
        // compute splits
        List<Split> splits = strat.split(payerId, amount.setScale(2, RoundingMode.HALF_UP), participants, extras);
        Expense e = new Expense(UUID.randomUUID().toString(), groupId, payerId, amount, description, splits);
        g.recordExpense(e);
        return e;
    }

    public void printUserBalances(String userId) {
        System.out.println("==== Balances for user: " + users.get(userId) + " ====");
        for (Group g : groups.values()) {
            if (g.members.contains(userId)) {
                g.printBalances(userId, users);
            }
        }
    }

    public void settleUp(String groupId, String fromUser, String toUser, BigDecimal amount) {
        Group g = groups.get(groupId);
        if (g == null) throw new NoSuchElementException("Group not found");
        boolean ok = g.settle(fromUser, toUser, amount.setScale(2, RoundingMode.HALF_UP));
        if (ok) System.out.println("Settle success: " + fromUser + " -> " + toUser + " : " + amount);
        else System.out.println("Settle failed (maybe nothing owed)");
    }

    public void printGroupExpenses(String groupId) {
        Group g = groups.get(groupId);
        if (g == null) throw new NoSuchElementException("Group not found");
        System.out.println("==== Expenses for group: " + g.name);
        for (Expense e : g.expenses) System.out.println(e);
    }
}
