package Model.Statements;

import Model.ADT.MyIDictionary;
import Model.Expressions.Exp;
import Model.FileTuple;
import Model.PrgState;

import java.io.BufferedReader;
import java.util.Objects;

public class ReadFile implements IStmt {

    private Exp exp_file_id;
    private String var_name;

    public ReadFile(Exp exp_file_id, String var_name) {
        this.exp_file_id = exp_file_id;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        MyIDictionary<Integer, FileTuple> fileTable=state.getFileTable();
        MyIDictionary<Integer,Integer> heap=state.getHeap();

        int fileid=exp_file_id.eval(symTable,heap);

        if(!fileTable.isDefined(fileid)){
            throw new Exception("This entry don't exists in File Table.\n");
        }
        BufferedReader buffer=fileTable.get(fileid).buffer;
        int nr=0;
        String line=buffer.readLine();
        if(!Objects.equals(line,null)){
            nr=Integer.parseInt(line);
        }
        if(symTable.isDefined(var_name))
            symTable.update(var_name,nr);
        else
            symTable.put(var_name,nr);
        return null;
    }

    @Override
    public String toString(){
        return "readFile("+this.exp_file_id.toString()+",\""+this.var_name+"\");";
    }
}
