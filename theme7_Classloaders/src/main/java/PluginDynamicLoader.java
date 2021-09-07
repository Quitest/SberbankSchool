import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PluginDynamicLoader extends ClassLoader {
    private final String[] classPath;
    private Map<String, Class<?>> classesHash = new HashMap<>();

    public PluginDynamicLoader(String[] classPath) {
        this.classPath = classPath;
    }

    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[(int) file.length()]; //WTF зачем при водим к (int)?
        FileInputStream fis = new FileInputStream(file);
        try {    //TODO переписать на try-with-resource
            fis.read(result, 0, result.length);
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                // Игнорируем исключения, возникшие при
                // вызове close. Они крайне маловероятны и не
                // очень важны - файл уже прочитан. Но если
                // они все же возникнут, то они не должны
                // замаскировать действительно важные ошибки,
                // возникшие при вызове read.
            }
        }
        return result;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        return super.findClass(name);
        Class<?> result = classesHash.get(name);
        if (result != null) {
            System.out.printf("[INFO]: Class %s found in cache.", name);
            return result;
        }
        File file = findFile(name.replace('.', '/'), ".class");
        // Класс mypackage.MyClass следует искать файле
        // mypackage/MyClass.class
        System.out.printf("[INFO] Class %s %s %n", name, (file == null ? "" : " found in " + file));
        if (file == null) {
            // Обращаемся к системному загрузчику в случае
            // неудачи. findSystemClass – это метод
            // абстрактного класса ClassLoader с объявлением
            // protected Class findSystemClass(String name)
            // (т.е. предназначенный для использования в
            // наследниках и не подлежащий переопределению).
            // Он выполняет поиск и загрузку класса по
            // алгоритму системного загрузчика. Без вызова
            // findSystemClass(name) нам пришлось бы
            // самостоятельно позаботиться о загрузке всех
            // стандартных библиотечных классов типа
            // java.lang.String, что потребовало бы
            // реализовать работу с JAR-архивами
            // (стандартные библиотеки почти всегда
            // упакованы в JAR)
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
        classesHash.put(name, result);
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
        File file;
        for (String s : classPath) {
            file = new File((new File(s)).getPath()
                    + File.separatorChar
                    + name.replace('/', File.separatorChar)
                    + extension);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    @Override
    protected URL findResource(String name) {
//        return super.findResource(name);
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
//        return super.loadClass(name, resolve);
        Class<?> result = findClass(name);
        if (resolve) {
            resolveClass(result);
        }
        return result;
    }
}
