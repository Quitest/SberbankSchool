package theme2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        System.out.println("\tЗадача 1. Разбить исходный список на группы по типу авто");
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
                    System.out.println("Доступные модели авто типа " + type + ":");
                    //Т.к. cars это List, то используем forEach повторно для вывода всех элементов List<Car>
                    cars.forEach(car -> System.out.println("\t" + car.getModel()));
                });
    }
}
