import interfaces.Plugin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public void initialize() {
        try {
            List<Path> pluginsList = Files.walk(Paths.get(pluginRootDirectory))
                    .filter(file -> file.getFileName().toString().endsWith(".class"))
//                    .map(Path::toString)
                    .collect(Collectors.toList());

            pluginsList.forEach(path -> load(
                    path.getFileName().toString(),
                    path.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Руки чешутся поменять контракт, но - моветон. Подумать над этим.
    public Plugin load(String pluginName, String pluginClassName) {
        //todo
        try {
            URL[] pluginURL = {Paths.get(pluginClassName).toUri().toURL()};
            ClassLoader classLoader = new URLClassLoader(pluginURL);
            classLoader.loadClass(pluginName);
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
