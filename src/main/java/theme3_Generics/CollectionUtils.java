package theme3_Generics;

import java.util.Comparator;
import java.util.List;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static List newArrayList() {
        return null;
    }

    public static int indexOf(List source, Object o) {
        return 0;
    }

    public static List limit(List source, int size) {
        return null;
    }

    public static void add(List source, Object o) {
    }

    public static void removeAll(List removeFrom, List c2) {
    }

    //true если первый лист содержит все элементы второго
    public static boolean containsAll(List c1, List c2) {
        return false;
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static boolean containsAny(List c1, List c2) {
        return false;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static List range(List list, Object min, Object max) {
        return null;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static List range(List list, Object min, Object max, Comparator comparator) {
        return null;
    }
}

