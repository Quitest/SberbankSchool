package theme3_Generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//FIXME принцип PECS не соблюдается. Поправить
/*
 * Значительная часть методов может быть реализована по аналогии с ArrayList - потребуется минимум модификации кода.
 * Т.к. тема "обобщенное программирование", а не "алгоритмы и структуры данных", и учитывая наличие метода newArrayList()
 * позволю себе опираться на существующие методы ArrayList.
 *
 */
public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<? extends T> limit(List<? extends T> source, int size) {
        return source.subList(0, size);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    //true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        return c2.stream()
                .anyMatch(c1::contains);
    }

    //TODO выбрать один вариант реализации методов range из всех надуманных. :)
    //TODO избавиться от дублирования кода - вызов одного метода из другого.
    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T extends Comparable<T>> List<T> range(List<T> list, T min, T max) {
        List<T> subList = new ArrayList<>();
        for (T e : list) {
            if (e.compareTo(min) >= 0 && e.compareTo(max) <= 0) {
                subList.add(e);
            }
        }
        subList.sort(Comparator.naturalOrder());
        return subList;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List<T> range(List<T> list, T min, T max, Comparator<T> comparator) {
        List<T> subList = new ArrayList<>();
        for (T e : list) {
            if (comparator.compare(e, min) >= 0 && comparator.compare(e, max) <= 0) {
                subList.add(e);
            }
        }
//        subList.sort(comparator);
        return subList;
    }

}

