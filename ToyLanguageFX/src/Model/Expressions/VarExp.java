package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.Exceptions.ADTException;
import Model.Exceptions.EvalException;

public class VarExp extends Exp {
    private String id;

    public VarExp(String i){
        this.id=i;
    }
    @Override
    public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> heap) throws EvalException,ADTException {
        try {
            if (tbl.isDefined(id))
                return tbl.get(id);
            else
                throw new EvalException("Evaluation exception : variabe not defined");
        }
        catch (ADTException e){
            throw new ADTException();
        }
    }
    public String toString() {
        return this.id;
    }
}
