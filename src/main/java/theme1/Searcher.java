package theme1;

/**
 * Класс "Поисковик" содержит метод поиска. Т.к. в будущем, возможно, будут задания на реализацию различных алгоритмов
 * поиска, решено их вынести в данный класс.
 */
public class Searcher {
    /**
     * Метод ищет значение в предварительно отсортированном массиве. Классическая реализация.
     * <a href = https://habr.com/ru/post/91698/>Источник алгоритма</a>
     *
     * @param array массив, в котором осуществляется поиск.
     * @param value искомое значение.
     * @return true - значение найдено, false - значение не найдено.
     */
    public static boolean binarySearch(Integer[] array, Integer value) {
        boolean found = false;
        int leftBound = 0;
        int rightBound = array.length - 1;

        while (leftBound <= rightBound && !found) {
            int middle = (leftBound + rightBound) / 2;
            if (array[middle].equals(value)) {
                found = true;
            } else {
                if (array[middle] < value) {
                    leftBound = middle + 1;
                } else {
                    rightBound = middle - 1;
                }
            }
        }
        return found;
    }

    /**
     * Метод ищет значение в предварительно отсортированном массиве подобно {@link #binarySearch(Integer[], Integer)},
     * но с добавлением творчества.
     * @param array предварительно отсортированный массив
     * @param value значение, которое необходимо найти
     * @param <T> тип массива и искомого значения
     * @return true - значение найдено, false - значение не найдено
     */
    public static <T extends Comparable<T>> boolean binarySearchGeneric(T[] array, T value) {
        //Цель проверки - оптимизация работы.
        // Выигрыш, предполагаю, будет тольок при частом вызове и/или на больших объемах данных.
        // Если значение value меньше минимального значения или больше максимального, то в массиве его быть не может
        // и искать его нет никакого смысла. Помним, что массив отсортирован.
        // Хотелось бы получить коментарии на это условие - корректное оно или нет? Может, при каких-то условиях
        // будет неверный результат?
        if (value.compareTo(array[0])<0 ||
        value.compareTo(array[array.length-1])>0){
            return false;
        }

        boolean found = false;
        int leftBound = 0;
        int rightBound = array.length - 1;

        while (leftBound <= rightBound && !found) {
            int middle = (leftBound + rightBound) / 2;
            if (array[middle].equals(value)) {
                found = true;
            } else {
                if (array[middle].compareTo(value) < 0) {
                    leftBound = middle + 1;
                } else {
                    rightBound = middle - 1;
                }
            }
        }
        return found;
    }
}
