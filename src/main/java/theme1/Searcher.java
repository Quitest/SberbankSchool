package theme1;

/**
 * Класс "Поисковик" содержит метод поиска. Т.к. в будущем, возможно, будут задания на реализацию различных алгоритмов
 * поиска, решено их вынести в данный класс.
 */
public class Searcher {
    /**
     * Метод ищет значение в предварительно отсортированном массиве.
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

//        L := 1; U := N; found := false;
//        while (L <= U) & not found do
//            M := середина отрезка L..U;
//        if a[M] = T then
//        found := true;
//        elseif a[M] < T then
//        L := M+1;
//  else
//        U := M-1;
//        end;
//        end

        return found;
    }

    public static <T extends Comparable<T>> boolean binarySearchGeneric(T[] array, T value) {
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
