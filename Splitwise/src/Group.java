import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Group {

    final String id;
    String name;
    // participants
    final Set<String> members = Collections.synchronizedSet(new LinkedHashSet<>());
    // expense history
    final List<Expense> expenses = Collections.synchronizedList(new ArrayList<>());
    // balances: balances.get(u).get(v) = amount u owes v (positive means u -> owes -> v)
    final Map<String, Map<String, BigDecimal>> balances = new ConcurrentHashMap<>();
    // lock for group-level operations
    final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMember(String userId) {
        members.add(userId);
        balances.putIfAbsent(userId, new ConcurrentHashMap<>());
        for (String member : members) {
            if (!member.equals(userId)) {
                balances.get(userId).putIfAbsent(member, BigDecimal.ZERO);
                balances.get(member).putIfAbsent(userId, BigDecimal.ZERO);
            }
        }
    }

    // update balances: adds amount owed from debtor -> creditor
    private void addDebt(String debtor, String creditor, BigDecimal amount){
        if (debtor.equals(creditor) || amount.compareTo(BigDecimal.ZERO) == 0) return;
        balances.get(debtor).putIfAbsent(creditor, BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        balances.get(creditor).putIfAbsent(debtor, BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        // increase debtor->creditor
        balances.get(debtor).put(creditor, balances.get(debtor).get(creditor).add(amount));
        // decrease creditor->debtor (mirror), to keep both sides consistent if you like; keep symmetrical invariant:
        BigDecimal mirror = balances.get(creditor).get(debtor).subtract(amount);
        // If mirror goes negative, we normalize:
        balances.get(creditor).put(debtor, mirror);
    }

    public boolean settle(String fromUser, String toUser, BigDecimal amount) {
        lock.writeLock().lock();
        try {
            if (!members.contains(fromUser) || !members.contains(toUser)) {
                return false;
            }
            BigDecimal owed = balances.get(fromUser).getOrDefault(toUser, BigDecimal.ZERO);

            if (owed.compareTo(BigDecimal.ZERO) <= 0) {
                return false; // fromUser does not owe toUser
            }
            BigDecimal paid = amount.min(owed);

            // reduce
            balances.get(fromUser).put(toUser, owed.subtract(paid));
            balances.get(toUser).put(fromUser, balances.get(toUser).get(fromUser).add(paid).negate()); // keep mirror consistent
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Map<String, BigDecimal> getUserBalanceRow(String userId) {
        lock.readLock().lock();
        try {
            return new HashMap<>(balances.getOrDefault(userId, Collections.emptyMap()));
        } finally {
            lock.readLock().unlock();
        }
    }

    public void recordExpense(Expense e) {

        lock.writeLock().lock();
        try {
            expenses.add(e);
            // apply splits: for each split: participant owes split.amount to payer (if participant != payer)
            for (Split s : e.splits) {
                String participant = s.userId;
                BigDecimal amt = s.amount.setScale(2, RoundingMode.HALF_UP);
                if (participant.equals(e.payerId)) continue;
                addDebt(participant, e.payerId, amt);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void printBalances(String userId, Map<String, User> userMap) {
        Map<String, BigDecimal> row = getUserBalanceRow(userId);
        System.out.println("Balances for " + userMap.get(userId) + " in group '" + name + "':");
        for (Map.Entry<String, BigDecimal> en : row.entrySet()) {
            if (en.getValue().compareTo(BigDecimal.ZERO) > 0) {
                System.out.println("  Owes " + userMap.get(en.getKey()) + " : " + en.getValue());
            } else if (en.getValue().compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("  Is owed by " + userMap.get(en.getKey()) + " : " + en.getValue().abs());
            }
        }
    }
}
