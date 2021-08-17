package theme1;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Sorter {

    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        int[] array = IntStream.generate(()->(int)(Math.random() * 10))
                .limit(10)
                .toArray();
        System.out.println(Arrays.toString(array));

        sorter.bubleSort(array);

        System.out.println(Arrays.toString(array));
    }
    //TODO массив - ссылочный тип. Проверить изменится ли состояние массива после вызова метода
    public int[] bubleSort(int[] array){
        for (int i=0;i< array.length;i++){
            for (int j= array.length-1; j>i; j--){
                if (array[j-1] > array[j]){
                    int tmp = array[j-1];
                    array[j-1]=array[j];
                    array[j]=tmp;
                }
            }
        }
        return array;
    }
}
