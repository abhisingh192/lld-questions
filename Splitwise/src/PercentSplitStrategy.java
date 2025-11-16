import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentSplitStrategy implements SplitStrategy{

    // extras should contain "percentMap": Map<String, BigDecimal> participant->percent (0-100)
    @Override
    public List<Split> split(String payerId, BigDecimal totalAmount, List<String> participants, Map<String,Object> extras) {
        Map<String, BigDecimal> perc = (Map<String, BigDecimal>) extras.get("percentMap");
        List<Split> out = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO;
        for (String p : participants) {
            BigDecimal percentage = perc.getOrDefault(p, BigDecimal.ZERO);
            BigDecimal amt = totalAmount.multiply(percentage).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            out.add(new Split(p, amt));
            sum = sum.add(amt);
        }
        // adjust rounding difference
        BigDecimal diff = totalAmount.subtract(sum);
        if (diff.compareTo(BigDecimal.ZERO) != 0) {
            // give leftover to first participant
            Split first = out.get(0);
            out.set(0, new Split(first.userId, first.amount.add(diff)));
        }
        return out;
    }
}
