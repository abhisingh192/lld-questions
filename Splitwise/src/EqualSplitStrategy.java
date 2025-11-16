import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{

    @Override
    public List<Split> split(String payerId, BigDecimal totalAmount, List<String> participants, Map<String,Object> extras) {
        int n = participants.size();
        BigDecimal[] shares = new BigDecimal[n];
        BigDecimal base = totalAmount.divide(BigDecimal.valueOf(n), 2, RoundingMode.DOWN);
        // distribute remainder pennies to first few participants
        BigDecimal distributed = base.multiply(BigDecimal.valueOf(n));
        BigDecimal remainder = totalAmount.subtract(distributed);
        for (int i=0;i<n;i++) shares[i]=base;
        int i=0;
        while (remainder.compareTo(BigDecimal.ZERO) > 0) {
            shares[i] = shares[i].add(new BigDecimal("0.01"));
            remainder = remainder.subtract(new BigDecimal("0.01"));
            i++;
        }
        List<Split> out = new ArrayList<>();
        for (int j=0;j<n;j++) out.add(new Split(participants.get(j), shares[j]));
        return out;
    }
}
