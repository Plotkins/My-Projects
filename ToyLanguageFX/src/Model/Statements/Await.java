package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.BarrierTuple;
import Model.FileTuple;
import Model.PrgState;
import javafx.fxml.FXML;

public class Await implements IStmt {
    private String id;

    public Await(String i){
        this.id=i;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        try {
            MyIDictionary<Integer,Integer> heap=state.getHeap();
            MyIDictionary<String,Integer> symTable=state.getSymTable();
            MyIDictionary<Integer, BarrierTuple> barrTable=state.getBarrierTable();
            MyIStack<IStmt> stack=state.getExeStack();

            if(!(symTable.keys().contains(id)))
                throw new Exception("The barrier id does not exist.!");
            if(!(symTable.keys().contains(id) && barrTable.keys().contains(symTable.get(id))))
                throw new Exception("The barrier does not exist.!");
            BarrierTuple barrier=barrTable.get(symTable.get(id));
            int length=barrier.barrierPool.size();
            System.out.println(length+"---------------------------------------------------------------------");
            if(length<barrier.barrierLength)
                if(barrier.barrierPool.contains(state.getId()))
                {
                    stack.push(this);
                }
                else{
                    barrTable.get(symTable.get(id)).barrierPool.add(state.getId());
                    stack.push(this);
                }
            return null;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public String toString(){
        return "await("+this.id+")";
    }
}
