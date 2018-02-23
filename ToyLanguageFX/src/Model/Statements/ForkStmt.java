package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.ADT.MyStack;
import Model.BarrierTuple;
import Model.FileTuple;
import Model.PrgState;

public class ForkStmt implements IStmt {

    private IStmt stmt;

    public ForkStmt(IStmt s){
        this.stmt=s;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {

        MyIList<Integer> out=state.getOut();
        MyIDictionary<Integer,Integer> heap=state.getHeap();
        MyIDictionary<String,Integer> newSymTable=state.getSymTable().clone();
        MyIDictionary<Integer, FileTuple> filetable=state.getFileTable();
        MyIDictionary<Integer, BarrierTuple> barrTable=state.getBarrierTable();

        int id=state.getNewId();

        MyIStack<IStmt> newExeStack=new MyStack<>();
        PrgState newPrgState=new PrgState(barrTable,newExeStack,newSymTable,out,filetable,heap,stmt,id);
        return newPrgState;
    }

    @Override
    public String toString(){
        return "fork( "+stmt.toString()+" )";
    }
}
