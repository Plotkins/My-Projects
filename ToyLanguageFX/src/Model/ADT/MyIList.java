package Model.ADT;

import Model.Exceptions.ADTException;

import java.util.ArrayList;

public interface MyIList<V> {
    void add(V value);
    void remove(int pos) throws ADTException;
    void update(int pos, V value) throws ADTException;
    int size();
    V get(int pos)throws ADTException;
    ArrayList<V> getAll();
}
