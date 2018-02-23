package Model.Statements;

import Model.PrgState;

public interface IStmt {
    @Override
    String toString();
    PrgState execute(PrgState state) throws Exception;
}
