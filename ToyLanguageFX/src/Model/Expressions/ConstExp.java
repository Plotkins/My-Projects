package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.Exceptions.ADTException;
import Model.Exceptions.EvalException;

public class ConstExp extends Exp{
    private int value;

    public ConstExp(int v){
        this.value=v;
    }
    @Override
    public int eval(MyIDictionary<String, Integer> tbl,MyIDictionary<Integer, Integer> heap) throws EvalException, ADTException {
        return this.value;
    }

    @Override
    public String toString(){
        return ""+this.value;
    }
}
