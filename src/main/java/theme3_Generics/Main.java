package theme3_Generics;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CountMap<Integer> map_src = new CountMapImpl<>();

        map_src.add(10);
        map_src.add(10);
        map_src.add(5);
        map_src.add(6);
        map_src.add(5);
        map_src.add(10);

        int count = map_src.getCount(5);  // 2
        int count1 = map_src.getCount(6);  // 1
        int count2 = map_src.getCount(10); // 3

        System.out.println("Проверка addAll(CountMap<K> source)");
        CountMap<Integer> map_addAll = new CountMapImpl<>();
        map_addAll.addAll(map_src);
        map_addAll.addAll(map_src);
        map_addAll.toMap().forEach((k, v) -> System.out.println("Key=" + k + " Value=" + v));

        System.out.println("\nПроверка remove()");
        System.out.println("Размер до удаления: " + map_addAll.size());
        System.out.println("Размер после удаления: " + map_addAll.remove(6));

        System.out.println("\nПроверка toMap()");
        Map<Integer, Integer> map_toMap = map_src.toMap();
        map_toMap.forEach((k, v) -> System.out.println("Key=" + k + " Value=" + v));

        System.out.println("\nПроверка toMap(Map<K, Integer> destination)");
        Map<Integer, Integer> map_toMapDst = new HashMap<>();
        map_src.toMap(map_toMapDst);
        map_toMapDst.forEach((k, v) -> System.out.println("Key=" + k + " Value=" + v));
    }
}
