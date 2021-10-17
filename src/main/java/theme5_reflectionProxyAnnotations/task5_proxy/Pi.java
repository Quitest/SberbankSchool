package theme5_reflectionProxyAnnotations.task5_proxy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.*;

@Profiling
public class Pi {
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FOUR = new BigDecimal(4);

    private Pi() {
    }

    // Gauss-Legendre Algorithm
    public static BigDecimal calc(final int SCALE) {
        BigDecimal a = ONE;
        BigDecimal b = ONE.divide(sqrt(TWO, SCALE), SCALE, RoundingMode.HALF_UP);
//        BigDecimal t = new BigDecimal(0.25);
        BigDecimal t = BigDecimal.valueOf(0.25);
        BigDecimal x = ONE;
        BigDecimal y;

        while (!a.equals(b)) {
            y = a;
            a = a.add(b).divide(TWO, SCALE, RoundingMode.HALF_UP);
            b = sqrt(b.multiply(y), SCALE);
            t = t.subtract(x.multiply(y.subtract(a).multiply(y.subtract(a))));
            x = x.multiply(TWO);
        }

        return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), SCALE, RoundingMode.HALF_UP);
    }

    // the Babylonian square root method (Newton's method)
    public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
        BigDecimal x0 = new BigDecimal("0");
//        BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(A.doubleValue()));

        while (!x0.equals(x1)) {
            x0 = x1;
//            x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
            x1 = A.divide(x0, SCALE, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
//            x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);
            x1 = x1.divide(TWO, SCALE, RoundingMode.HALF_UP);
        }

        return x1;
    }
}
