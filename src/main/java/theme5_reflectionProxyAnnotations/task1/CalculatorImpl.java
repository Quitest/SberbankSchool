package theme5_reflectionProxyAnnotations.task1;

import theme5_reflectionProxyAnnotations.task5_CacheProxy.Cacheable;

import java.util.stream.IntStream;

public class CalculatorImpl implements Calculator {
    @Cacheable
    @Override
    public int calc(int number) {
        return IntStream.rangeClosed(1, number)
                .reduce(1, (x1, x2) -> x1 * x2);
    }

    @Override
    public int calcCacheable(int number) {
        return IntStream.rangeClosed(1, number)
                .reduce(1, (x1, x2) -> x1 * x2);
    }
}
