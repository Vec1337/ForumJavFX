package hr.javafx.domain.entities;

import java.util.Map;

public class Generic2 <K, V>{

    private Map<K, V> map;

    public Generic2(Map<K, V> map) {
        this.map = map;
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

    public V get(K key) {
        return map.get(key);
    }

    public Map<K, V> getMap() {
        return map;
    }

}
