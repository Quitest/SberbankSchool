import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        ClassLoader loader;
        while (true) {
//            loader = new PluginDynamicLoader(new String[]{"theme7_Classloaders/pluginRootDirectory/Plugin1"});
            loader = new PluginDynamicLoader(new String[]{"."});
            Class<?> aClass = Class.forName("Plugin1", true, loader);
            Object o = aClass.getDeclaredConstructor().newInstance();
            System.out.println(o);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
    }

}
