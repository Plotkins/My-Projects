package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;

public class NewH implements IStmt {

    private Exp exp;
    private String var;

    public NewH(String v, Exp e){
        this.var=v;
        this.exp=e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {

        MyIDictionary<String,Integer> symTable=state.getSymTable();
        MyIDictionary<Integer,Integer> heap=state.getHeap();

        if(symTable.isDefined(var)){
            symTable.remove(var);
        }

        int pos=state.getHeapTopAddr();
        state.incAddr();
        int val=exp.eval(symTable,heap);

        heap.put(pos,val);
        symTable.put(var,pos);

        return null;
    }

    @Override
    public String toString(){
        return  "new("+this.var+","+this.exp.toString()+")";
    }
}
