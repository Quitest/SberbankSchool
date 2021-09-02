package theme5_reflectionProxyAnnotations.task1;

import java.util.stream.IntStream;

public class CalculatorImpl implements Calculator {

    @Override
    public int calc(int number) {
        return IntStream.rangeClosed(1, number)
                .reduce(1, (x1, x2) -> x1 * x2);
    }

    @Override
    public int calcWithoutAnnotation(int number) {
        return IntStream.rangeClosed(1, number)
                .reduce(1, (x1, x2) -> x1 * x2);
    }
}
