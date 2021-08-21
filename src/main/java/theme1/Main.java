package theme1;

import theme1.shapes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static final int MIN_GEN_VAL = -10;
    public static final int MAX_GEN_VAL = 10;
    public static final int GENERATION_LIMIT = 10;

    public static void main(String[] args) {
        System.out.println("\t1.1 СОРТИРОВКА ПУЗЫРЬКОМ");
        System.out.println("Пример Integer[]");
        Integer[] arrayInteger = Stream
                //Взамен сложной "математической" гнерации интервала можно использовать класс Random, например
                .generate(() -> (int) (Math.random() * (MAX_GEN_VAL - MIN_GEN_VAL) + MIN_GEN_VAL))
                .limit(GENERATION_LIMIT)
                .toArray(Integer[]::new);
        System.out.println("Исходный: " + Arrays.toString(arrayInteger));
        Sorter.bubleSortGeneric(arrayInteger);
        System.out.println("Отсортированный: " + Arrays.toString(arrayInteger));

        System.out.println("\nПример Byte[].");
        Byte[] arrayByte = Stream.generate(() -> (byte) (Math.random() * (MAX_GEN_VAL - MIN_GEN_VAL) + MIN_GEN_VAL))
                .limit(GENERATION_LIMIT)
                .toArray(Byte[]::new);
        System.out.println("Исходный: " + Arrays.toString(arrayByte));
        Sorter.bubleSortGeneric(arrayByte);
        System.out.println("Отсортированный: " + Arrays.toString(arrayByte));
//----------------------------------------------------------------------------------------------------------------------
        System.out.println("\n\t1.2 БИНАРНЫЙ ПОИСК в предварительно отсотированных массивах");
        System.out.println("Пример поиска в массиве Integer " + Arrays.toString(arrayInteger));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите искомый элемент: ");
        Integer val = Integer.parseInt(scanner.nextLine());
        System.out.println("Элемент " +
                (Searcher.binarySearchGeneric(arrayInteger, val) ? // возможно, не самый читаемый код, но захотелось так попробовать :)
                        "найден" : "НЕ найден") + " методом Searcher.binarySearchGeneric");

        System.out.print("\nПример поиска в массиве String ");
        //генерируем случайные строки длинной до GENERATION_LIMIT. Сделано что бы руками не набирать.
        RandomString strGen = new RandomString(GENERATION_LIMIT);
        String[] arrayString = new String[MAX_GEN_VAL];
        for (int i = 0; i < arrayString.length; i++) {
            arrayString[i] = strGen.nextString();
        }

        Sorter.bubleSortGeneric(arrayString);
        System.out.println(Arrays.toString(arrayString));
        System.out.print("Введите искомый элемент: ");
        String str = scanner.nextLine();
        //TODO на досуге детально разобраться с тем как работает бинарный поиск в массиве отсортированных строк.
        // сейчас нет полного понимания. Обратить внимание на compareTo в реализации сортировки.
        System.out.println("Элемент " +
                (Searcher.binarySearchGeneric(arrayString, str) ?
                        "найден" : "НЕ найден") + " методом Searcher.binarySearchGeneric");
//----------------------------------------------------------------------------------------------------------------------
        System.out.println("\n\t2. Реализация иерархии объектов\nПример вывода");
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(new Circle(4));
        shapeList.add(new Triangle(2, 1, 2));
        shapeList.add(new Rect(2, 5));
        shapeList.add(new Square(5));
//        shapeList.add(new Square(-4)); //пример с недопустимым аргументом
        System.out.println("<Площадь> | <Периметр> | <Тип>");
        for (Shape shape : shapeList) {
            System.out.printf("%9.3f | %10.3f | %s %n",
                    shape.getArea(),
                    shape.getPerimeter(),
                    shape.getClass().getSimpleName());
        }
    }
}
