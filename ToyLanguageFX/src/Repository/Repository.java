package Repository;

import Model.Exceptions.ADTException;
import Model.PrgState;
import Model.Statements.IStmt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> states;
    private String filename;

    public Repository(String fn){

        this.filename=fn;
        this.states=new ArrayList<PrgState>();
    }

    @Override
    public List<PrgState> getPrgList() throws ADTException {
        return states;
    }

    @Override
    public void setPrgList(List<PrgState> l) {
        states=l;
    }

    @Override
    public void logPrgStateExec(PrgState state) throws Exception {
        try(PrintWriter logfile= new PrintWriter(new BufferedWriter(new FileWriter(filename,true))))
        {
            logfile.append(state.display());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void addState(PrgState state) {
        states.add(state);
    }

    @Override
    public PrgState getProgramStateWithId(int id) {
        for(PrgState pr : states)
            if(pr.getId() == id)
                return pr;
        return null;
    }

}
