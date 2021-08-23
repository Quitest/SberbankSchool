package theme2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    private static final int LIMIT = 20; //устанавливает ограничение на количество элементов, выдаваемых в потоках.

    public static void main(String[] args) {
        System.out.println("Задача 1. Разбить исходный список на группы по типу авто.");
        //Создаем исходный список авто
        List<Car> carList = new ArrayList<Car>() {{
            add(new Car("Лада", "седан"));
            add(new Car("Лада", "хэтчбек"));
            add(new Car("Мерседес", "седан"));
            add(new Car("Бмв", "кроссовер"));
            add(new Car("Форд", "хэтчбек"));
            add(new Car("Форд", "хэтчбек"));
            add(new Car("Пежо", "кроссовер"));
            add(new Car("Тойота", "седан"));
            add(new Car("Вольво", "грузовик"));
            add(new Car("Камаз", "грузовик"));
        }};

        //Группируем авто по типам
        Map<String, List<Car>> carsGroupedByType = carList.stream()
                .collect(groupingBy(Car::getType));

        carsGroupedByType.forEach(
                (type, cars) -> {
                    System.out.println("\tДоступные модели авто типа " + type + ":");
                    //Т.к. cars это List, то используем forEach повторно для вывода всех элементов List<Car>
                    cars.forEach(car -> System.out.println("\t\t" + car.getModel()));
                });
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("Задача 2. Анализ текста.");
        TextAnalizer analizer = new TextAnalizer(Paths.get("story.txt"), StandardCharsets.UTF_8);
        Map<String, Long> words = analizer.analize();

        System.out.println("2.1. Количество различных слов в файле: " + words.keySet().size());

        System.out.println("2.2. Список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала " +
                "по длине слова, потом по тексту) [первые " + LIMIT + " слов]:");
        words.keySet()
                .stream()
                .sorted(Comparator.comparingLong(String::length).thenComparing(Comparator.naturalOrder()))
                .limit(LIMIT)
                .forEach(w -> System.out.println("\t" + w));
        System.out.println("\t...");

        System.out.println("2.3. Подсчитать и вывести на экран сколько раз каждое слово встречается в файле " +
                "[первые " + LIMIT + " слов]:");
        System.out.printf("\t%-12s | %s %n", "<Слово>", "<Количество>");
        words.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .limit(LIMIT)
                .forEach(entry -> System.out.printf("\t%-12s | %2d%n", entry.getKey(), entry.getValue()));
        System.out.println("\t...");

        System.out.println("2.4. Выведите на экран все строки файла в обратном порядке.");
        List<String> reversedText = analizer.getText();
        Collections.reverse(reversedText);
        reversedText.stream()
                .limit(LIMIT)
                .forEachOrdered(x -> System.out.println("\t" + x));

        //Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
        System.out.println("2.5. Реализуйте свой Iterator для обхода списка в обратном порядке.");
        Iterator<String> it = analizer.iterator();
        while (it.hasNext()) {
            System.out.println("\t" + it.next());
        }

        System.out.println("2.6. Вывести на экран строки, номера которых задаются пользователем в произвольном порядке.");
        //Вариант 1. Простой вывод
        List<String> stringList = analizer.getStrings(0, 1, 2, 10, 3, 5, 3);
        stringList.forEach(line -> System.out.println("\t" + line));

        //Вариант 2. Наглядный вывод
//        int[] lineNumbers = {0,1,2,10,3,5,3};
//        List<String> stringList = analizer.getStrings(lineNumbers);
//        for (int i=0; i<stringList.size();i++){
//            System.out.printf("\t[%d] %s %n", lineNumbers[i]+1, stringList.get(i));
//        }

    }
}
