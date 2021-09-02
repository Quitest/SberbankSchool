package theme5_reflectionProxyAnnotations.task2_4;

import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        //Задача 2
        //Немного трудно читаемого кода - это для практики работы с потоками.
        System.out.println("----------TASK 2----------");
        GetMethodsInfo.getAllMethods(5).forEach((k, v) -> v.forEach(m -> System.out.printf("%s <--- %s %n", k, m)));

        //Задача 3
        System.out.println("----------TASK 3----------");
        GetMethodsInfo.getGetters(new GregorianCalendar()).forEach(System.out::println);

        //Задача 4
        System.out.println("----------TASK 4----------");
        System.out.println(GetMethodsInfo.isNameEqualsValue(new Fields()));
    }
}
