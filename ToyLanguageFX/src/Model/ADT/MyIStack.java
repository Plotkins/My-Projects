package Model.ADT;

import Model.Exceptions.ADTException;

import java.util.Stack;

public interface MyIStack<V> {
    void push(V value) throws ADTException;
    V pop() throws ADTException;
    V top() throws ADTException;
    Boolean isEmpty();
    String toString();
    Stack<V> getStack();
}
