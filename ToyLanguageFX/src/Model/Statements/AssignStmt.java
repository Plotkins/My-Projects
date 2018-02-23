package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.PrgState;

public class AssignStmt implements IStmt{
    private String id;
    private Exp expr;
    public AssignStmt(String i,Exp e){
        this.id=i;
        this.expr=e;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        MyIDictionary<Integer,Integer> heap=state.getHeap();
        int e=this.expr.eval(symTable,heap);
        if(symTable.isDefined(id)) symTable.update(id,e);
        else symTable.put(id,e);
        return null;
    }

    @Override
    public String toString(){
        return  id+"="+ expr.toString();
    }
}
