package theme5_reflectionProxyAnnotations.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 7:
 Реализовать следующий класс по документации
*/
public class BeanUtils {
    public static void main(String[] args) {
//        getMethodsByType(new GregorianCalendar(), "set").forEach(System.out::println);
        assign(new GregorianCalendar(), new GregorianCalendar());
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
            Method correspondGetter = gettersFrom.stream()
                    .filter(m -> m.getName().substring(2).equals(setter.getName().substring(2)))
                    .findFirst().get();
            try {
                Object result = correspondGetter.invoke(from);
                setter.invoke(to,result);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    //Возможно, стоило бы ограничить количество вариантов methodType.
    public static List<Method> getMethodsByType(Object obj, String methodType) {
        Method[] methods = obj.getClass().getMethods();
//        String mType = methodType == MethodType.GETTER ? "get" : "set";
        return Arrays.stream(methods)
                .filter(method -> method.getName().startsWith(methodType))
//                .map(Method::toString) // для вывода полной сигнатуры
//                .map(Method::getName) //вывод только имени геттера
                .collect(Collectors.toList());
    }

    private static String getSignature(Method method){
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName())
                .append(Arrays.toString(method.getParameters()));
        String s = sb.toString();
        return s;
    }
}

