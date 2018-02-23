package Model.ADT;

import Model.Exceptions.ADTException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyDictionary<K,V> implements MyIDictionary<K,V>{

    HashMap<K,V> map;

    public HashMap<K, V> getMap() {
        return map;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return this.map.entrySet();
    }

    public MyDictionary(){
        map=new HashMap<K,V>();
    }
    public MyDictionary(HashMap<K,V> dic){
        map=dic;
    }
    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public void put(K key, V value) throws ADTException {
        if(this.map.containsKey(key))
            throw new ADTException("Dictionary exception: the key already exists");
        map.put(key,value);
    }

    @Override
    public void update(K key, V value) throws ADTException {
        if(this.map.containsKey(key)==false)
            throw new ADTException("Dictionary exception: the key doesn't exist");
        map.replace(key,value);
    }

    @Override
    public boolean isDefined(K key) {
        return map.containsKey(key);
    }

    @Override
    public V get(K key) throws ADTException {
        if(map.isEmpty())
            throw new ADTException("Dictionary exception: the dictionary is empty");
        if(map.containsKey(key)==false)
            throw new ADTException("Dictionary exception: the key doesn't exist");
        return map.get(key);
    }

    @Override
    public void remove(K key) throws ADTException {
        if(map.isEmpty())
            throw new ADTException("Dictionary exception: the dictionary is empty");
        if(map.containsKey(key)==false)
            throw new ADTException("Dictionary exception: the key doesn't exist");
        map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public List<K> keys() throws ADTException{
        if(map.isEmpty())
            throw new ADTException("dictionary is empty");
        return new ArrayList<>(map.keySet());
    }

    @Override
    public List<V> values()throws ADTException{
        if(map.isEmpty())
            throw new ADTException("dictionary is emty");
        return new ArrayList<>(map.values());
    }

    @Override
    public MyIDictionary<K,V>  clone(){
        MyIDictionary<K,V> newDict= new MyDictionary<>(new HashMap<>());
        try {
            for (K key : map.keySet())
                newDict.put(key, map.get(key));
        }
        catch (ADTException e) {
            System.out.println("stai chill");
        }
        return newDict;
    }
}
