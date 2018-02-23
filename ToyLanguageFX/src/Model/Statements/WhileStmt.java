package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Exceptions.ADTException;
import Model.Expressions.Exp;
import Model.PrgState;

public class WhileStmt implements IStmt {

    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp e, IStmt s){
        this.exp=e;
        this.stmt=s;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        try{
            MyIDictionary<String,Integer> symTable=state.getSymTable();
            MyIDictionary<Integer,Integer> heap=state.getHeap();
            MyIStack<IStmt> stack=state.getExeStack();

            int e=exp.eval(symTable,heap);
            if(e!=0){
                stack.push(this);
                stack.push(this.stmt);
            }
            return null;
        }
        catch(ADTException e){
            throw new Exception(e);
        }
    }

    @Override
    public String toString(){
        return "( while( "+exp.toString()+" ) "+stmt.toString()+" )";
    }
}
