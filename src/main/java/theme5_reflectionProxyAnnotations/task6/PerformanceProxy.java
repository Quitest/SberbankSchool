package theme5_reflectionProxyAnnotations.task6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//TODO ознакомиться, разобраться https://docs.oracle.com/javase/7/docs/technotes/guides/reflection/proxy.html
public class PerformanceProxy implements InvocationHandler {

    private final Object obj;

    private PerformanceProxy(Object obj) {
        this.obj = obj;
    }

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new PerformanceProxy(obj));
    }

    public Object invoke(Object proxy, Method m, Object[] args)
            throws Throwable {
        Object result;
        long timeBefore = System.nanoTime();
        try {
            result = m.invoke(obj, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " +
                    e.getMessage());
        } finally {
            long timeAfter = System.nanoTime();
            if (m.getAnnotation(Metric.class) != null) {
                System.out.printf("Время работы метода: %d %n", timeAfter - timeBefore);
            }
        }
        return result;
    }
}
