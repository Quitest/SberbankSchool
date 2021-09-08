import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {

        PluginManager pluginManager = new PluginManager("D:\\JavaProjects\\SberbankSchool\\theme7_Classloaders\\pluginRootDirectory");
//        System.out.println(
//                pluginManager.load("n", "Plugin1.Plugin1"));
        pluginManager.loadAllPlugins();


        /*        ClassLoader loader;
        while (true) {
//            loader = new howto.PluginDynamicLoader(new String[]{"theme7_Classloaders/pluginRootDirectory/PluginsExamples.Plugin1.Plugin1"});
//            loader = new PluginDynamicLoader(new String[]{"."});
            loader = new PluginLoader(".");
            Class<?> aClass = Class.forName("howto.TestModule", true, loader);
//            Class<?> aClass = loader.loadClass("howto.TestModule");
            Object o = aClass.getDeclaredConstructor().newInstance();
//            Object o = aClass.newInstance();
            TestModule tm = (TestModule)o;
            System.out.println(o);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }*/
    }

}
