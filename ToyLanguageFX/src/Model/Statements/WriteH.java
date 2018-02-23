package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;

public class WriteH implements IStmt {
    private Exp exp;
    private String var;

    public WriteH(String v, Exp e){
        this.var=v;
        this.exp=e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        MyIDictionary<Integer,Integer> heap=state.getHeap();
        int val=exp.eval(symTable,heap);
        if(symTable.isDefined(var)){
            int pos=symTable.get(var);
            if(heap.isDefined(pos)){
                heap.update(pos,val);
            }
            else{
                throw new Exception("The variable from variable address doesn't exits!");
            }
        }
        else{
            throw new Exception("The variable doesn't exists!");
        }
        return null;
    }

    @Override
    public String toString(){
        return  "wH("+this.var+","+this.exp.toString()+")";
    }
}
