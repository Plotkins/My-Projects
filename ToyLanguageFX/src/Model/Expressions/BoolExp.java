package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.Exceptions.ADTException;
import Model.Exceptions.EvalException;

import java.util.Objects;

public class BoolExp extends Exp {

    private int op;
    private Exp e1;
    private Exp e2;

    public BoolExp(String o, Exp exp1, Exp exp2){
        this.e1=exp1;
        this.e2=exp2;
        if(Objects.equals(o,"<"))
            this.op=1;
        if(Objects.equals(o,"<="))
            this.op=2;
        if(Objects.equals(o,"=="))
            this.op=3;
        if(Objects.equals(o,"!="))
            this.op=4;
        if(Objects.equals(o,">="))
            this.op=5;
        if(Objects.equals(o,">"))
            this.op=6;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> heap) throws EvalException, ADTException {
        try {
            if(op==1)
                return (e1.eval(tbl,heap)<e2.eval(tbl,heap))? 1: 0;
            if(op==2)
                return (e1.eval(tbl,heap)<=e2.eval(tbl,heap))? 1: 0;
            if(op==3)
                return (e1.eval(tbl,heap)==e2.eval(tbl,heap))? 1: 0;
            if(op==4)
                return (e1.eval(tbl,heap)!=e2.eval(tbl,heap))? 1: 0;
            if(op==5)
                return (e1.eval(tbl,heap)>=e2.eval(tbl,heap))? 1: 0;
            if(op==6)
                return (e1.eval(tbl,heap)>e2.eval(tbl,heap))? 1: 0;
            return -1;
        }
        catch (Exception e){
            throw new EvalException("");
        }
    }

    @Override
    public String toString() {
        if(op==1)
            return (e1.toString()+"<"+e2.toString());
        if(op==2)
            return (e1.toString()+"<="+e2.toString());
        if(op==3)
            return (e1.toString()+"=="+e2.toString());
        if(op==4)
            return (e1.toString()+"!="+e2.toString());
        if(op==5)
            return (e1.toString()+">="+e2.toString());
        if(op==6)
            return (e1.toString()+">"+e2.toString());
        return "";
    }
}
