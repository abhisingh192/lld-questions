import java.math.BigDecimal;
import java.math.RoundingMode;

public class Split {

    final String userId;
    final BigDecimal amount; // amount owed by this user

    public Split(String userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Split{" +
                "userId='" + userId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
