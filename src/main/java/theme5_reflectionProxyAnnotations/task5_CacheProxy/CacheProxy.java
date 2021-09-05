package theme5_reflectionProxyAnnotations.task5_CacheProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

//TODO попробовать написать дженерик CacheProxy
public class CacheProxy implements InvocationHandler {
    private final Object obj;
    private HashMap<Object[], Object> memoryCache;

    private CacheProxy(Object obj) {
        this.obj = obj;
        memoryCache = new HashMap<>();
    }

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new CacheProxy(obj));
    }

    private Object fileCache(Method method, Object[] args) {
        Object resultToCache = new Object();
        //TODO Все попытки реализовать красивый кэш путем записи объектов потерпели неудачи.Пока что...
        // Идею записи пары примитивов key=value, реализовать несложно и неинтересно. Поэтому файловый кэш пока отложу
        // до отпуска. :)
        return resultToCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        if (cacheable == null) {
            return method.invoke(obj, args);
        }

        //Сделано через switch, т.к. предполагается реализация третьего варианта кэша
        switch (cacheable.cacheType()) {
            case RAM -> result = ramCache(method, args);
            case FILE -> result = fileCache(method, args);
        }

        return result;
    }

    private Object ramCache(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Object fromCache = memoryCache.keySet().parallelStream() //Пытаюсь использовать параллельные потоки для ускорения поиска.
                .filter(k -> Arrays.equals(k, args))
                .findFirst().orElse(null); //FIXME Переделать - теряется весь смысл Optional'а
        if (fromCache != null) {
            return memoryCache.get(fromCache);
        }

        Object resultToCache = method.invoke(obj, args);
        memoryCache.put(args, resultToCache);
        return resultToCache;
    }
}


