package theme1;

public class Sorter {
    /**
     * Попытка реализации обобщенного метода сортировки пузырьком.
     *
     * @param array сортируемый массив
     * @param <T>   наследник java.lang.Number, реализующий java.lang.Comparable
     */
    public static <T extends Comparable<T>> void bubleSortGeneric(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1].compareTo(array[j]) > 0) {
                    T tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    /**
     * Метод сортировки целочисленного массива пузырьком.
     *
     * @param array соритруемый массив
     */
    public static void bubleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }
}


