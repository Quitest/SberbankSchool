package theme1;

public class Sorter {
    /*public static final int MIN_GEN_VAL = -10;
    public static final int MAX_GEN_VAL = 10;
    public static final int GENERATION_LIMIT = 10;*/

    /*public static void main(String[] args) {
        System.out.println("Пример Integer.");
        Integer[] arrayInteger = Stream
                .generate(() -> (int) (Math.random() * (MAX_GEN_VAL - MIN_GEN_VAL) + MIN_GEN_VAL))
                .limit(GENERATION_LIMIT)
                .toArray(Integer[]::new);
        System.out.println("Исходный: " + Arrays.toString(arrayInteger));
        Sorter.bubleSortGeneric(arrayInteger);
        System.out.println("Отсортированный: " + Arrays.toString(arrayInteger));

        System.out.println("\nПример Byte.");
        Byte[] arrayByte = Stream.generate(() -> (byte) (Math.random() * (MAX_GEN_VAL - MIN_GEN_VAL) + MIN_GEN_VAL))
                .limit(GENERATION_LIMIT)
                .toArray(Byte[]::new);
        System.out.println("Исходный: " + Arrays.toString(arrayByte));
        Sorter.bubleSortGeneric(arrayByte);
        System.out.println("Отсортированный: " + Arrays.toString(arrayByte));
    }*/

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


