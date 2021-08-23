package theme3_Generics;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T o) {
        map.merge(o, 1, Integer::sum);
    }

    @Override
    public void addAll(CountMap<T> source) {
        for (Map.Entry<T, Integer> entry : source.toMap().entrySet()) {
            map.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    @Override
    public int getCount(T o) {
        return map.get(o);
    }

    @Override
    public int remove(T o) {
        return map.remove(o);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Map<T, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(map);
    }


}
