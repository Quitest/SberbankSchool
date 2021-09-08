import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PluginLoader extends ClassLoader {
    private final String pluginRootDirectory;
    private final Map<String, Class<?>> classesCache = new HashMap<>();

    public PluginLoader(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[(int) file.length()]; //WTF получается, что максимальный размер файла для загрузки ограничен Integer.MAX_VALUE? В таком случа необходимо читать файлы через буферизированный ридер в цикле?
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(result, 0, result.length);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            FileNotFoundException fnfe = new FileNotFoundException();
            e.initCause(e);
            throw fnfe;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            IOException ioe = new IOException();
            e.initCause(e);
            throw ioe;
        }
        return result;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> result = classesCache.get(name);
        if (result != null) {
            System.out.printf("[INFO]: Class %s found in cache.", name);
            return result;
        }
        File file = findFile(name.replace('.', '/'), ".class");
        // Класс mypackage.MyClass следует искать файле
        // mypackage/MyClass.class
//        System.out.printf("[INFO] Class %s %s %n", name, (file == null ? "" : " found in " + file));
        if (file == null) {
            // Обращаемся к системному загрузчику в случае неудачи.
            return findSystemClass(name);
        }
        try {
            byte[] classBytes = loadFileAsBytes(file);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Can not load class " + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + name + ": " + e);
        }
        classesCache.put(name, result);
        return result;
    }

    /**
     * Поиск файла с именем name и, возможно, расширением
     * extension в каталогах поиска, заданных параметром
     * конструктора classPath. Имена подкаталогов в name
     * разделяются символом '/' – даже если в операционной
     * системе принят другой разделитель для подкаталогов.
     * (Именно в таком виде получает свой параметр метод
     * findResource.)
     */
    private File findFile(String name, String extension) {
        File file = Paths.get(
                pluginRootDirectory,
                File.separator,
                name.replace('/',File.separatorChar),
                extension).toFile();
        if (file.exists()) {
            return file;
        }
        return null;
    }

    @Override
    protected URL findResource(String name) {
        File file = findFile(name, "");
        if (file == null) {
            return null;
        }
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            return null; // FIXME: 07.09.2021 Подавление - плохо - исправить.
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> result = findClass(name);
        if (resolve) {
            resolveClass(result);
        }
        return result;
    }
}
