package theme5_reflectionProxyAnnotations.task6;

import theme5_reflectionProxyAnnotations.task1.Calculator;
import theme5_reflectionProxyAnnotations.task1.CalculatorImpl;

//TODO полезная информация:
// https://www.baeldung.com/java-dynamic-proxies
// https://habr.com/ru/company/otus/blog/434214/
public class Main {

    public static void main(String[] args) {
        final int NUMBER = 7;
        //FIXME не в полной мере соответствует заданию. Необходимо через конструктор работу выполнить.
        Calculator c = (Calculator) PerformanceProxy.newInstance(new CalculatorImpl());
        System.out.println("Calc без аннтации:");
        System.out.println(c.calcCacheable(NUMBER));
        System.out.println("Calc с анотацией:");
        System.out.println(c.calc(NUMBER));

//        Calculator calculator = new PerformanceProxy(new Calculator());
//        System.out.println(calculator.calc(3));

//        Вывод:
//        Время работы метода: ХХХХХ наносек
//        6
    }
}
