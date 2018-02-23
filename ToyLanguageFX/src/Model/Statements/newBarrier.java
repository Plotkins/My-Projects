package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.BarrierTuple;
import Model.Expressions.Exp;
import Model.FileTuple;
import Model.PrgState;

import java.util.ArrayList;
import java.util.List;

public class newBarrier implements IStmt {
    private String id;
    private Exp exp;

    public newBarrier(String i,Exp e){
        this.id=i;
        this.exp=e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        try{
            MyIDictionary<Integer, FileTuple> fileTable=state.getFileTable();
            MyIDictionary<String,Integer> symTable=state.getSymTable();
            MyIDictionary<Integer,Integer> heap=state.getHeap();
            MyIDictionary<Integer,BarrierTuple> barrierTable=state.getBarrierTable();
            int e=exp.eval(symTable,heap);


            int pos=state.getBarrerTopAddr();
            state.incBarrierAddr();

            BarrierTuple newBarrier=new BarrierTuple(pos,e,new ArrayList<Integer>());
            barrierTable.put(pos,newBarrier);
            symTable.put(id,pos);

            return null;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public String toString(){
        return "newBarrier("+id+","+exp.toString()+")";
    }
}
