package theme5_reflectionProxyAnnotations.task7_BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/*
Задача 7:
 Реализовать следующий класс по документации
*/
//TODO не хватает универсальности. При возможности дописать.
public class BeanUtils {
    public static void main(String[] args) {
        ClassSettersGetters to = new ClassSettersGetters();
        ClassGetters from = new ClassGetters(11, "str");

        System.out.println("ДО: " + to.getInteger() + " | " + to.getString());
        assign(to, from);
        System.out.println("ПОСЛЕ: " + to.getInteger() + " | " + to.getString());

    }

    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        List<Method> gettersFrom = getMethodsByType(from, "get");
        List<Method> settersTo = getMethodsByType(to, "set");
        for (Method setter : settersTo) {
            try {
                Method correspondGetter = gettersFrom.stream()
                        .filter(m -> m.getName().substring(2).equals(setter.getName().substring(2)))
                        .findFirst().get();
                Object result = correspondGetter.invoke(from);//FIXME падает при наличии параметров
                setter.invoke(to, result);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e) {
            }
        }
    }

    //TODO Возможно, стоило бы ограничить количество вариантов methodType.
    public static List<Method> getMethodsByType(Object obj, String methodType) {
        Method[] methods = obj.getClass().getMethods();
        return Arrays.stream(methods)
                .filter(method -> method.getName().startsWith(methodType))
//                .map(Method::toString) // для вывода контракта + модификаторов
//                .map(Method::getName) //вывод только имени геттера
                .collect(Collectors.toList());
    }
}

