package Model.Exceptions;

public class MyStmtExecException extends Exception{
    public MyStmtExecException(){
        super("Program state is over.\n");

    }
}
