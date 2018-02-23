package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Exceptions.ADTException;
import Model.Expressions.Exp;
import Model.PrgState;

public class IfStmt implements IStmt {
    private Exp expr;
    private IStmt thenS;
    private IStmt elseS;
    public IfStmt(Exp e,IStmt thS,IStmt elS){
        this.expr=e;
        this.thenS=thS;
        this.elseS=elS;
    }
    @Override
    public String toString(){
        return  "IF("+ expr.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+")";
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        try{
            MyIDictionary<String,Integer> symTable=state.getSymTable();
            MyIDictionary<Integer,Integer> heap=state.getHeap();

            int e=expr.eval(symTable,heap);
            MyIStack<IStmt> stack=state.getExeStack();
            if(e!=0)
                stack.push(this.thenS);
            else
                stack.push(this.elseS);
            return null;
        }
        catch(ADTException e){
            throw new Exception(e);
        }
    }
}
