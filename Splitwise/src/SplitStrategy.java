import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    // produce list of Split objects for participants (including payer); amounts must sum to total
    List<Split> split(String payerId, BigDecimal totalAmount, List<String> participants, Map<String, Object> extras);
}
