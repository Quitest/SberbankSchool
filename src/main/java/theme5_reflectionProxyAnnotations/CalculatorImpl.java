package theme5_reflectionProxyAnnotations;

import java.util.stream.IntStream;

//TODO полезная информация:
// https://www.baeldung.com/java-dynamic-proxies
// https://habr.com/ru/company/otus/blog/434214/

public class CalculatorImpl implements Calculator {

    public static void main(String[] args) {
        final int NUMBER = 7;

        System.out.println("--------PerformanceProxy");
        //FIXME не в полной мере соответствует заданию. Необходимо через конструктор работу выполнить.
        Calculator c = (Calculator) PerformanceProxy.newInstance(new CalculatorImpl());
        System.out.println("Calc без аннтации:");
        System.out.println(c.calcWithoutAnnotation(NUMBER));
        System.out.println("Calc с анотацией:");
        System.out.println(c.calc(NUMBER));

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

    @Override
    public int calcWithoutAnnotation(int number) {
        return IntStream.rangeClosed(1, number)
                .reduce(1, (x1, x2) -> x1 * x2);
    }
}
