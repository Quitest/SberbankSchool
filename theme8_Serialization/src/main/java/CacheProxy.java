import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CacheProxy implements InvocationHandler {

    public static Object cache(Object obj){
        return null;
    }

    //TODO 9. CacheProxy должен тоже принимать в конструкторе некоторые настройки, например рутовую папку в которой
    // хранить файлы, дефолтные настройки кеша и тд.
    public CacheProxy() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
