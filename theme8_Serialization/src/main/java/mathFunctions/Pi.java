package mathFunctions;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.*;

public class Pi {
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FOUR = new BigDecimal(4);

/*    public static void main(String[] args) {
        System.out.println(pi(Integer.parseInt(args[0])));
    }*/

    // Gauss-Legendre Algorithm
    public static BigDecimal pi(final int SCALE) {
        BigDecimal a = ONE;
//        BigDecimal b = ONE.divide(SquareRoot.sqrt(TWO, SCALE), SCALE, ROUND_HALF_UP);
        BigDecimal b = ONE.divide(SquareRoot.sqrt(TWO, SCALE), SCALE, RoundingMode.HALF_UP);
        BigDecimal t = new BigDecimal("0.25");
        BigDecimal x = ONE;
        BigDecimal y;

        while (!a.equals(b)) {
            y = a;
//            a = a.add(b).divide(TWO, SCALE, ROUND_HALF_UP);
            a = a.add(b).divide(TWO, SCALE, RoundingMode.HALF_UP);
            b = SquareRoot.sqrt(b.multiply(y), SCALE);
            t = t.subtract(x.multiply(y.subtract(a).multiply(y.subtract(a))));
            x = x.multiply(TWO);
        }

//        return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), SCALE, ROUND_HALF_UP);
        return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), SCALE, RoundingMode.HALF_UP);
    }



}
