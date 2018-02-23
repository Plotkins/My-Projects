package Model;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.Exceptions.ADTException;
import Model.Exceptions.MyStmtExecException;
import Model.Statements.IStmt;

public class PrgState {

    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable;
    private MyIList<Integer> out;
    private MyIDictionary<Integer, FileTuple> fileTable;
    private MyIDictionary<Integer,Integer> heap;
    private MyIDictionary<Integer,BarrierTuple> barrierTable;
    private IStmt originalProgram;
    private int heapTopAddr=1;
    private int barrierTopAddr=1;
    private int id;
    private int idGiver=1;

    public PrgState() {
    }

    public MyIDictionary<Integer, BarrierTuple> getBarrierTable() {
        return barrierTable;
    }

    public void setBarrierTable(MyIDictionary<Integer, BarrierTuple> barrierTable) {
        this.barrierTable = barrierTable;
    }

    public MyIDictionary<Integer, FileTuple> getFileTable() {
        return fileTable;
    }

    public PrgState(MyIDictionary<Integer,BarrierTuple> barrTable,MyIStack<IStmt> stk, MyIDictionary<String,Integer> symtbl, MyIList<Integer> ot, MyIDictionary<Integer, FileTuple> file, MyIDictionary<Integer,Integer> h,IStmt stmt, int id) throws ADTException {
        this.exeStack=stk;
        stk.push(stmt);
        this.symTable=symtbl;
        this.out = ot;
        this.originalProgram=stk.top();
        this.fileTable=file;
        this.heap=h;
        this.id=id;
        this.barrierTable=barrTable;
    }

    public int getHeapTopAddr() {
        return heapTopAddr;
    }
    public void incAddr(){
        this.heapTopAddr+=1;
    }

    public int getBarrerTopAddr() {
        return barrierTopAddr;
    }
    public void incBarrierAddr(){
        this.barrierTopAddr+=1;
    }

    public String display(){

        String result;
        result="\n\n\tProgram id: "+this.id;
        result+="\n\tExecution stack: "+exeStack.toString();

        result+="\n\tSymbol table: ";
        try {
            for(String i:symTable.keys())
                result=result+"\n"+i+"->"+symTable.get(i);
        } catch (ADTException e) {
            result+=e.getMessage();
        }

        result+="\n\tOutput list: ";
        if(out.size()!=0)
            for(int i:out.getAll())
                result=result+"\n"+i;
        else
            result+="list is empty";
        result+="\n\tFile table: ";
        try {
            for(Integer i:fileTable.keys())
                result+="\n"+i+"->"+fileTable.get(i).fileName;
        } catch (ADTException e) {
            result+=e.getMessage();
        }

        result+="\n\tHeap table: ";
        try {
            for(Integer i:heap.keys())
                result=result+"\n"+i+"->"+this.heap.get(i);
        } catch (ADTException e) {
            result+=e.getMessage();
        }

        return result;
    }

    public Boolean isNotCompleted(){
        if(!exeStack.isEmpty())
            return true;
        else
            return false;
    }

    public void setFileTable(MyIDictionary<Integer, FileTuple> fileTable) {
        this.fileTable = fileTable;
    }

    public PrgState oneStep() throws Exception {
        if(exeStack.isEmpty()){
            throw new MyStmtExecException();
        }
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);

    }

    public int getId(){return this.id; }

    public int getNewId(){
        this.idGiver*=10;
        return id*idGiver;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Integer> getOut() {
        return out;
    }

    public void setOut(MyIList<Integer> out) {
        this.out = out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setHeap(MyIDictionary<Integer, Integer> heap) {
        this.heap = heap;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public MyIDictionary<Integer, Integer> getHeap() {
        return heap;
    }

}
