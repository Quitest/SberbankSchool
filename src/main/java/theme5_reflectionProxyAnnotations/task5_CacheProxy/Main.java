package theme5_reflectionProxyAnnotations.task5_CacheProxy;

import theme5_reflectionProxyAnnotations.task1.Calculator;
import theme5_reflectionProxyAnnotations.task1.CalculatorImpl;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = (Calculator) CacheProxy.newInstance(new CalculatorImpl());
        calculator.calcCacheable(1);
        calculator.calcCacheable(2);
        calculator.calcCacheable(3);
        calculator.calcCacheable(4);
        calculator.calcCacheable(5);
        calculator.calcCacheable(6);
    }
}
