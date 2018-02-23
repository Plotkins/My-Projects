package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.Exceptions.ADTException;
import Model.Exceptions.EvalException;

public abstract class Exp {
    public abstract int eval(MyIDictionary<String, Integer> tbl,MyIDictionary<Integer, Integer> heap) throws EvalException, ADTException;
    public abstract String toString();
}
