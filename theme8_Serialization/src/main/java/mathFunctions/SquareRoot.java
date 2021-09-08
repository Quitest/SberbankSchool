package mathFunctions;

import java.math.BigDecimal;
import java.math.RoundingMode;

//import static java.math.BigDecimal.ROUND_HALF_UP; //оригинал

public class SquareRoot {
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FOUR = new BigDecimal(4);

    // the Babylonian square root method (Newton's method)
    public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(A.doubleValue()));

        while (!x0.equals(x1)) {
            x0 = x1;
//            x1 = A.divide(x0, SCALE, ROUND_HALF_UP); // Оригинал
            x1 = A.divide(x0, SCALE, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
//            x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP); // Оригинал
            x1 = x1.divide(TWO, SCALE, RoundingMode.HALF_UP);
        }

        return x1;
    }
}
