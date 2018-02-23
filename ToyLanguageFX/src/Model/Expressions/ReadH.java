package Model.Expressions;

import Model.ADT.MyIDictionary;
import Model.Exceptions.ADTException;
import Model.Exceptions.EvalException;

public class ReadH  extends Exp{

    private String var;

    public ReadH(String v){
        this.var=v;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> tbl, MyIDictionary<Integer, Integer> heap) throws EvalException, ADTException {
        try{
            if(tbl.isDefined(this.var)){
                int pos=tbl.get(this.var);
                if (heap.isDefined(pos)) {
                    int value=heap.get(pos);
                    return value;
                }
                else{
                    throw new EvalException("There is no value for the value's address .");
                }
            }
            else
            {
                throw new EvalException("The variable is not defined.");
            }
        }
        catch (Exception e)
        {
            throw new EvalException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return " rH("+var+") ";
    }
}
