package theme5_reflectionProxyAnnotations;

import java.lang.reflect.Proxy;
import java.util.stream.IntStream;

//TODO полезная информация:
// https://www.baeldung.com/java-dynamic-proxies
// https://habr.com/ru/company/otus/blog/434214/

public class CalculatorImpl implements Calculator {
    public static void main(String[] args) {
        CalculatorImpl calcImpl = new CalculatorImpl();
        TimingInvocationHandler handler = new TimingInvocationHandler(calcImpl);

        Calculator calc = (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[]{Calculator.class},
                handler
        );
        System.out.println(calc.calc(3));
//        Calculator calculator = new PerformanceProxy(new Calculator());
//        System.out.println(calculator.calc(3));

//        Вывод:
//        Время работы метода: ХХХХХ наносек
//        6
    }

    @Override
    public int calc(int number) {
        return IntStream.rangeClosed(1, number)
                .reduce(1, (x1, x2) -> x1 * x2);
    }
}
