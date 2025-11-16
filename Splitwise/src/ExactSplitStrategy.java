import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExactSplitStrategy implements SplitStrategy {
    // extras should contain "exactMap": Map<String, BigDecimal> participant->amount
    @SuppressWarnings("unchecked")
    @Override
    public List<Split> split(String payerId, BigDecimal totalAmount, List<String> participants, Map<String,Object> extras) {
        Map<String, BigDecimal> exact = (Map<String, BigDecimal>) extras.get("exactMap");
        List<Split> out = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO;
        for (String p : participants) {
            BigDecimal amt = exact.getOrDefault(p, BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
            out.add(new Split(p, amt));
            sum = sum.add(amt);
        }
        if (sum.compareTo(totalAmount.setScale(2, RoundingMode.HALF_UP)) != 0) {
            throw new IllegalArgumentException("Exact splits don't add up to totalAmount. sum=" + sum + " total=" + totalAmount);
        }
        return out;
    }


}
