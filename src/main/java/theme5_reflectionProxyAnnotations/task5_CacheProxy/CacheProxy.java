package theme5_reflectionProxyAnnotations.task5_CacheProxy;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

//TODO попробовать написать дженерик CacheProxy
public class CacheProxy implements InvocationHandler {
    private final Object obj;
    private final HashMap<Object, Object> memoryCache;

    private CacheProxy(Object obj) {
        this.obj = obj;
        memoryCache = new HashMap<>(5,2);
    }

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new CacheProxy(obj));
    }

    @Deprecated
    //Не работает потому что :)
    private Object fileCache(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Object cached = method.invoke(obj, args);
        // FIXME: 02.09.2021 Сейчас тут заглушка. Сделать запись в файл.
        // http://www.tutorialspoint.com/java/java_serialization.htm
        String pathToCache = method.getAnnotation(Cacheable.class).pathToCache();
        try {
            ObjectOutput cacheOutput = new ObjectOutputStream(new FileOutputStream(pathToCache));
//            AbstractMap.SimpleEntry<Object, Object> row = new AbstractMap.SimpleEntry<>(obj,cached);
//            Map<Object,Object> row = new HashMap<>(5, 2);
            memoryCache.put(obj,cached);
            cacheOutput.writeObject(memoryCache);
            cacheOutput.flush();
//            memoryCache.put(obj, cached);
//            dataOutput.writeUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cached;
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
        Object cached;
        if ((cached = memoryCache.get(obj)) != null) { //Если есть в кэше, то берем и возвращаем из него
            return cached;
        }

        cached = method.invoke(obj, args);
        memoryCache.put(obj, cached);
        return cached;
    }
}
