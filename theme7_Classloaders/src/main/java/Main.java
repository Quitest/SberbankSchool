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
//            Class<?> aClass = Class.forName("TestModule", true, loader);
            Class<?> aClass = loader.loadClass("TestModule");
            Object o = aClass.getDeclaredConstructor().newInstance();
//            Object o = aClass.newInstance();
            TestModule tm = (TestModule)o;
            System.out.println(o);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
    }

}
