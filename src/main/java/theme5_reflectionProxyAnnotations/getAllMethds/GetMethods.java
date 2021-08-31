package theme5_reflectionProxyAnnotations.getAllMethds;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


public class GetMethods {
    /**
     * Получает все методы класса, включая все родительские методы (включая приватные)
     * @param obj
     * @return Map key - имя класса-владельца. val - список методов.
     */
    public static Map<String, List<Method>> getAllMethods(Object obj) {
        Class<?> clazz = obj.getClass();
        Map<String, List<Method>> map = new HashMap<>();
        List<Method> currMethods;
        do {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            currMethods = Arrays.stream(declaredMethods)
                    .sorted(Comparator.comparing(Method::getModifiers).thenComparing(Method::getName))
                    .collect(Collectors.toList());
            map.put(clazz.getCanonicalName(), currMethods);
        } while ((clazz = clazz.getSuperclass()) != null);
        return map;
    }

    /**
     * Задача 3:
     * Вывести все геттеры класса.
     * Считается что "геттер" - это метод, в имени которого имеется слово get.
     * @param obj геттеры чего надо получить
     * @return список всех геттеров
     */
    public static List<String> getGetters(Object obj){
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        return Arrays.stream(declaredMethods)
                .filter(method -> method.getName().contains("get"))
//                .map(Method::toString) // для вывода полной сигнатуры
                .map(Method::getName) //вывод только имени геттера
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        //Немного трудно читаемого кода - это для практики работы с потоками.
//        GetMethods.getAllMethods(5).forEach((k, v) -> v.forEach(m -> System.out.printf("%s <--- %s %n", k, m)));

        GetMethods.getGetters(new GregorianCalendar()).forEach(System.out::println);
    }
}
