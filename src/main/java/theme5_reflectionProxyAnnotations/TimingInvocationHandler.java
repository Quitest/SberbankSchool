package theme5_reflectionProxyAnnotations;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalTime;

public class TimingInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long before = System.nanoTime();
        Object result = method.invoke(target, args);
        System.out.printf("Время работы метода: %d нс %n", System.nanoTime() - before);
        return result;
    }
}
