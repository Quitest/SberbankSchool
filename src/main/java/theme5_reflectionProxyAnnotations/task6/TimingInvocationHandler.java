package theme5_reflectionProxyAnnotations.task6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimingInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        Optional<Object> result = Optional.empty();
        Object result;
        Metric annotationMetric = method.getAnnotation(Metric.class);

        long before = System.nanoTime();
        result = method.invoke(target, args);
        long after = System.nanoTime();
        if (annotationMetric != null) {
            System.out.printf("Время работы метода: %d нс %n", after - before);
        }
        return result;
    }
}
