package Model.ADT;

import Model.Exceptions.ADTException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MyIDictionary<K,V> {
    boolean isEmpty();
    void put(K key, V value) throws ADTException;
    V get(K key) throws ADTException;
    void remove(K key)throws ADTException;
    int size();
    List<K> keys() throws ADTException;
    List<V> values() throws ADTException;
    MyIDictionary clone();
    void update(K key, V value) throws ADTException;
    boolean isDefined(K key);
    public HashMap<K, V> getMap();
    Iterable<Map.Entry<K, V>> getAll();
}
