package Repository;

import Model.Exceptions.ADTException;
import Model.PrgState;
import Model.Statements.IStmt;

import java.util.List;

public interface IRepository {
    List<PrgState> getPrgList() throws ADTException;
    void setPrgList(List<PrgState> l);
    void logPrgStateExec(PrgState state) throws Exception;
    void addState(PrgState state);
    PrgState getProgramStateWithId(int id);

}
