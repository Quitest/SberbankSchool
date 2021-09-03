package theme5_reflectionProxyAnnotations.task5_CacheProxy;

import theme5_reflectionProxyAnnotations.task1.Calculator;
import theme5_reflectionProxyAnnotations.task1.CalculatorImpl;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = (Calculator) CacheProxy.newInstance(new CalculatorImpl());
        calculator.calcCacheable(5);
    }
}
