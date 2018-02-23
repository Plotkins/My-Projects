package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.Expressions.Exp;
import Model.PrgState;

public class PrintStmt implements IStmt {
    public Exp expr;

    public PrintStmt(Exp e){
        this.expr=e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        MyIDictionary<Integer,Integer> heap=state.getHeap();

        int e=expr.eval(symTable,heap);
        MyIList<Integer> out=state.getOut();
        out.add(e);
        return null;
    }

    @Override
    public String toString(){
        return "print(" +expr.toString()+")";
    }
}
