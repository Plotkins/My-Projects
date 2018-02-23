package Model.Statements;

import Model.ADT.MyIStack;
import Model.Exceptions.ADTException;
import Model.PrgState;

public class CmpStmt implements IStmt{
    private IStmt first;
    private IStmt second;

    public CmpStmt(IStmt s1,IStmt s2){
        first=s1;
        second=s2;
    }

    public PrgState execute(PrgState state) throws Exception{
        try {
            MyIStack<IStmt> stack = state.getExeStack();
            stack.push(second);
            stack.push(first);
            return null;
        }
        catch(ADTException e) {
            throw new Exception(e);
        }
    }

    @Override
    public String toString() {
        return first.toString() + "; " + second.toString();
    }
}
