import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

public class Expense {

    final String id;
    final String groupId;
    final String payerId;
    final BigDecimal amount;
    final String description;
    final Instant time;
    final List<Split> splits; // each participant's share (amounts)

    public Expense(String id, String groupId, String payerId, BigDecimal amount, String description, List<Split> splits) {
        this.id = id;
        this.groupId = groupId;
        this.payerId = payerId;
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.description = description;
        this.time = Instant.now();
        this.splits = splits;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", payerId='" + payerId + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", splits=" + splits +
                '}';
    }
}
