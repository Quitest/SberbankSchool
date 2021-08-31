package theme5_reflectionProxyAnnotations.getAllMethds;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/*
Вывести на консоль все методы класса, включая все родительские методы
(включая приватные)
 */
public class GetMethods {
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

    public static void main(String[] args) {
        Integer i = 5;
        //Немного трудно читаемого кода - это для практики работы с потоками.
        GetMethods.getAllMethods(i).forEach((k, v) -> v.forEach(m -> System.out.printf("%s <--- %s %n", k, m)));
    }
}
