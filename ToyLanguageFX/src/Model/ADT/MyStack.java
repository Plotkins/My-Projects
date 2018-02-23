package Model.ADT;

import Model.Exceptions.ADTException;

import java.util.Stack;

public class MyStack<V> implements MyIStack<V>{

    private Stack<V> stack;
    public MyStack(){
        stack=new Stack<V>();
    }
    public MyStack(Stack<V> s){
        this.stack=s;
    }
    @Override
    public void push(V value) throws ADTException {
        if(value==null) throw new ADTException("cannot push a null element");
        else
            this.stack.push(value);
    }

    @Override
    public Stack<V> getStack() {
        return (Stack<V>)stack.clone();
    }

    @Override
    public Boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public V pop() throws ADTException{
        if(stack.empty())
            throw new ADTException("Stack exception catched: stack is empty");
        return stack.pop();
    }

    @Override
    public V top() throws ADTException{
        if(stack.empty()) throw new ADTException("Stack exception catched: stack is empty");
        return stack.peek();
    }
    @Override
    public String toString() {

        Stack<V> copyStack=(Stack<V>)stack.clone();
        String result=new String();
        if(copyStack.size()==0)
            result+="stack is empty";
        while(!copyStack.isEmpty())
        {
            result+="\n"+copyStack.pop();
        }
        return result;
    }
}