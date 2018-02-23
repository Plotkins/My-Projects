package Model.Statements;


import Model.ADT.MyIDictionary;
import Model.FileTuple;
import Model.PrgState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class OpenRFile implements IStmt {
    private String fileId;
    private String filename;

    public OpenRFile(String fi, String fn) {
        this.fileId = fi;
        this.filename = fn;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {

        MyIDictionary<Integer, FileTuple> fileTable=state.getFileTable();
        MyIDictionary<String,Integer> symTable=state.getSymTable();

        if(fileTable.size()>0)
            for(Integer i:fileTable.keys()){
                FileTuple file=fileTable.get(i);
                if(Objects.equals(file.fileName,filename)){
                    throw new Exception("Filename already exists.");
                }
            }
        try {
            System.out.println("hheeeei\n");
            BufferedReader in = new BufferedReader(new FileReader(filename));
            FileTuple newFile=new FileTuple(filename,in);

            int pos=fileTable.size();
            fileTable.put(pos,newFile);
            if(symTable.isDefined(fileId)) symTable.update(fileId,pos);
            else symTable.put(fileId,pos);


        } catch (IOException error1) {
            System.out.println("File Not Found");
        }

        return null;
    }

    @Override
    public String toString(){
        return "openRFile("+this.fileId+",\""+this.filename+"\");";
    }
}
