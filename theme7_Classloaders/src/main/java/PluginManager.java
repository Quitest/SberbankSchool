import interfaces.Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PluginManager {
    private final String pluginRootDirectory;
    private final PluginLoader pluginLoader;
    private final Map<String, Plugin> pluginMap;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
        pluginLoader = new PluginLoader(pluginRootDirectory);
        pluginMap = new HashMap<>();
    }

    public Map<String, Plugin> getPluginMap() {
        return pluginMap;
    }

    public Plugin load(String pluginName, String pluginClassName) {
        Plugin loadedPlugin = null;
        try {
            Class<?> aClass = Class.forName(pluginClassName, false, pluginLoader);
            loadedPlugin = (Plugin) aClass.getDeclaredConstructor().newInstance();
            pluginMap.putIfAbsent(pluginName, loadedPlugin);
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
                Plugin loadedPlugin = load(name, name.replace(File.separator, "."));
                String canonicalName = loadedPlugin.getClass().getCanonicalName();
            }

        } catch (NoSuchFileException nsfe) {
            System.err.printf("Директория %s не найдена.", nsfe.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadAllPlugins() {
        pluginMap.clear();
        loadAllPlugins();
    }
}
