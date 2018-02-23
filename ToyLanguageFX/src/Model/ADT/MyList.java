package Model.ADT;

import Model.Exceptions.ADTException;

import java.util.ArrayList;

public class MyList<V> implements MyIList<V> {
    private ArrayList<V> list;
    public MyList(){
        this.list=new ArrayList<>();
    }

    public MyList(ArrayList<V> l){
        this.list=l;
    }

    @Override
    public void add(V value) {
        this.list.add(value);
    }

    @Override
    public void remove(int pos) throws ADTException {
        if(pos<0 || pos >=list.size()) throw new ADTException("position to remove is invalid");
        list.remove(pos);
    }

    @Override
    public void update(int pos, V value) throws ADTException {
        if(pos<0 || pos >=list.size()) throw new ADTException("position to update is invalid");
        list.set(pos,value);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public V get(int pos) throws ADTException {
        if(pos<0 || pos >=list.size()) throw new ADTException("position to get is invalid");
        return list.get(pos);
    }
    @Override
    public ArrayList<V> getAll(){
        return new ArrayList<>(list);
    }
}
