import interfaces.Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PluginManager {
    private final String pluginRootDirectory;
    private final PluginLoader pluginLoader;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
        pluginLoader = new PluginLoader(pluginRootDirectory);
    }

    public Plugin load(String pluginName, String pluginClassName) {
        Plugin loadedPlugin = null;
        try {
            Class<?> aClass = Class.forName(pluginClassName, false, pluginLoader);
            loadedPlugin = (Plugin) aClass.getDeclaredConstructor().newInstance();
            // FIXME: 08.09.2021 Написать нормальную обработку.
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return loadedPlugin;
    }

    public void loadAllPlugins() {
        try {
            List<String> pluginsList = Files.walk(Paths.get(pluginRootDirectory))
                    .filter(fileName -> fileName.toString().endsWith(".class"))
                    .map(path -> path.getName(path.getNameCount() - 2).resolve(path.getFileName()))
                    .map(path -> path.toString().replaceAll(".class", ""))
                    .collect(Collectors.toList());

            for (String name : pluginsList) {
                //TODO В класслоадере происходит обратная процедура '.'->'File.separator'. Попробовать избавиться.
                Plugin plugin = load(name, name.replace(File.separator, "."));
                plugin.doUsefull();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
