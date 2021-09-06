package theme5_reflectionProxyAnnotations.task5_CacheProxy;

import org.junit.jupiter.api.Test;
import theme5_reflectionProxyAnnotations.task1.Calculator;
import theme5_reflectionProxyAnnotations.task1.CalculatorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheProxyTest {

    @Test
    void invoke() {
        Calculator calculator = (Calculator) CacheProxy.newInstance(new CalculatorImpl());
        int r;
        r = calculator.calcCacheable(1);
        assertEquals(1, r);
        r = calculator.calcCacheable(1);
        assertEquals(1, r);
        r = calculator.calcCacheable(1);
        assertEquals(1, r);
        r = calculator.calcCacheable(2);
        assertEquals(2, r);
        r = calculator.calcCacheable(3);
        assertEquals(6, r);
        r = calculator.calcCacheable(4);
        assertEquals(24, r);
        r = calculator.calcCacheable(5);
        assertEquals(120, r);
        r = calculator.calcCacheable(6);
        assertEquals(720, r);


    }
}