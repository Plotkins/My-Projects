package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.FileTuple;
import Model.PrgState;

import java.io.BufferedReader;

public class CloseRFile implements IStmt{

    private Exp exp_file_id;
    public CloseRFile(Exp exp_file_id) {
        this.exp_file_id = exp_file_id;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<Integer, FileTuple> fileTable=state.getFileTable();
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        MyIDictionary<Integer,Integer> heap=state.getHeap();

        int fileId=exp_file_id.eval(symTable,heap);
        int fileid=exp_file_id.eval(symTable,heap);
        if(!fileTable.isDefined(fileid)){
            throw new Exception("This entry don't exists in File Table.\n");
        }
        BufferedReader buffer=fileTable.get(fileid).buffer;
        buffer.close();
        fileTable.remove(fileId);
        return null;
    }

    @Override
    public String toString() {
        return "closeRFile(" + this.exp_file_id.toString() + ")";
    }
}
