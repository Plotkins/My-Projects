package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Expressions.BoolExp;
import Model.Expressions.Exp;
import Model.PrgState;


public class SwitchStmt implements IStmt {
    IStmt stmt1;
    IStmt stmt2;
    IStmt stmt3;
    Exp exp;
    Exp exp1;
    Exp exp2;
    public SwitchStmt(Exp e,Exp e1,Exp e2,IStmt s1,IStmt s2,IStmt s3){
        this.exp=e;
        this.exp1=e1;
        this.exp2=e2;
        this.stmt1=s1;
        this.stmt2=s2;
        this.stmt3=s3;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        try{
            MyIDictionary<String,Integer> symTable=state.getSymTable();
            MyIDictionary<Integer,Integer> heap=state.getHeap();
            MyIStack<IStmt> stack=state.getExeStack();
            IStmt newStmt=new IfStmt(new BoolExp("==",exp,exp1),stmt1,new IfStmt(new BoolExp("==",exp,exp2),stmt2,stmt3));
            stack.push(newStmt);
            return null;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public String toString(){
        return "switch("+exp.toString()+")(case "+exp1.toString()+" : "+stmt1.toString()+")(case "+exp2.toString()+" : "+stmt2.toString()+")(default :"+stmt3.toString()+")";
    }
}
