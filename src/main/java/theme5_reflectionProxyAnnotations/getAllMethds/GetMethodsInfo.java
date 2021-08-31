package theme5_reflectionProxyAnnotations.getAllMethds;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;


public class GetMethodsInfo {
    /**
     * Получает все методы класса, включая все родительские методы (включая приватные)
     *
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
     *
     * @param obj геттеры чего надо получить
     * @return список всех геттеров
     */
    public static List<String> getGetters(Object obj) {
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        return Arrays.stream(declaredMethods)
                .filter(method -> method.getName().contains("get"))
//                .map(Method::toString) // для вывода полной сигнатуры
                .map(Method::getName) //вывод только имени геттера
                .collect(Collectors.toList());
    }

    /**
     * Задача 4:
     * Проверить что все String константы имеют значение = их имени
     * public static final String MONDAY = "MONDAY";
     *
     * @param obj объект поля, которого проверяем
     * @return true - если условия задачи выполняются, false - в противном случае.
     */
    public static boolean isNameEqualsValue(Object obj) throws IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            int modifiers = field.getModifiers();
            String type = field.getType().getName();
            //Если поле имеет модификаторы public static final и тип String, то проверяем его. Иначе пропускаем
            if ((modifiers & Modifier.PUBLIC) > 0 &&
                    (modifiers & Modifier.STATIC) > 0 &&
                    (modifiers & Modifier.FINAL) > 0 &&
                    "java.lang.String".equals(type)) {
                field.setAccessible(true);
                if (!field.getName().equals(field.get(obj))) {
                    return false; //при первом различии имени поля и его значения выходим
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IllegalAccessException {
        //Задача 2
        //Немного трудно читаемого кода - это для практики работы с потоками.
        GetMethodsInfo.getAllMethods(5).forEach((k, v) -> v.forEach(m -> System.out.printf("%s <--- %s %n", k, m)));

        //Задача 3
        GetMethodsInfo.getGetters(new GregorianCalendar()).forEach(System.out::println);

        //Задача 4
        System.out.println(GetMethodsInfo.isNameEqualsValue(new Fields()));
    }
}
