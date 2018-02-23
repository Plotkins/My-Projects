package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.Exceptions.ADTException;
import Model.Exceptions.EvalException;

public class ArithExp extends Exp {
    private Exp e1;
    private Exp e2;
    private int op;

    public ArithExp(Character o, Exp ex1, Exp ex2){
        this.e1=ex1;
        this.e2=ex2;
        if(o=='+')
            this.op=1;
        if(o=='-')
            this.op=2;
        if(o=='*')
            this.op=3;
        if(o=='/')
            this.op=4;
    }
    @Override
    public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> heap) throws EvalException, ADTException {
        try {
            if(op==1)
                return (e1.eval(tbl,heap)+e2.eval(tbl,heap));
            if(op==2)
                return (e1.eval(tbl,heap)-e2.eval(tbl,heap));
            if(op==3)
                return (e1.eval(tbl,heap)*e2.eval(tbl,heap));
            if(op==4)
                return (e1.eval(tbl,heap)/e2.eval(tbl,heap));
            return -1;
        }
        catch (Exception e){
            throw new EvalException("");
        }
    }

    @Override
    public String toString(){
        if(op==1)
            return (e1.toString()+"+"+e2.toString());
        if(op==2)
            return (e1.toString()+"-"+e2.toString());
        if(op==3)
            return (e1.toString()+"*"+e2.toString());
        if(op==4)
            return (e1.toString()+"/"+e2.toString());
        return "";
    }
}
