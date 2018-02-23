package sample;

import Controller.Controller;
import Model.ADT.MyDictionary;
import Model.ADT.MyIDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.BarrierTuple;
import Model.Exceptions.ADTException;
import Model.Expressions.*;
import Model.FileTuple;
import Model.PrgState;
import Model.Statements.*;
import Repository.IRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    List<IStmt> states;
    public static Controller ctrl;

    @FXML
    private ListView<String> stateList;

    @FXML
    private Button selectButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            intitializePrgList();
        } catch (ADTException e) {
            e.printStackTrace();
        }
        ObservableList<String> items = FXCollections.observableArrayList(getStringRepresentations());
        stateList.setItems(items);
        selectButton.setOnAction(actionEvent-> {
            int index = stateList.getSelectionModel().getSelectedIndex();
            if(index < 0)
                return;
            try {
                PrgState initialProgramState = new PrgState(new MyDictionary<Integer, BarrierTuple>(), new MyStack<IStmt>(), new MyDictionary<String, Integer>(), new MyList<Integer>(), new MyDictionary<Integer, FileTuple>(), new MyDictionary<Integer, Integer>(), states.get(index), index + 1);
                IRepository repository = new Repository("log" + index + ".txt");
                repository.addState(initialProgramState);
                Controller ctrl1 = new Controller(repository);
                ctrl=ctrl1;
                FXMLLoader fxmlloader=new FXMLLoader(getClass().getResource("second_window.fxml"));
                Parent root1= null;
                root1 = (Parent) fxmlloader.load();

                Stage stage=new Stage();
                stage.setTitle("Mare");
                stage.setScene(new Scene(root1));

                stage.show();
            } catch (ADTException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    private List<String> getStringRepresentations(){
        return states.stream().map(IStmt::toString).collect(Collectors.toList());
    }


    public void intitializePrgList() throws ADTException {

        IStmt stmt= new CmpStmt(new AssignStmt("a", new ArithExp('-',new ConstExp(2), new
                ConstExp(2))),
                new CmpStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));



        IStmt stmt1= new CmpStmt(new OpenRFile("file1","F:\\Facultate\\MAP\\ToyLanguage\\src\\readMe.txt"),
                new CmpStmt(new ReadFile(new VarExp("file1"),"v"),new CloseRFile(new VarExp("file1"))));

        IStmt stmt2=new CmpStmt(new AssignStmt("v",new ConstExp(20)),
                new CmpStmt(new NewH("v",new ConstExp(20)),
                        new CmpStmt(new NewH("a",new ConstExp(66)),
                                new CmpStmt(new WriteH("v",new ConstExp(33)),
                                        new CmpStmt(new PrintStmt(new ReadH("v")),
                                                new CmpStmt(new PrintStmt(new VarExp("v")),
                                                        new CmpStmt(new AssignStmt("a",new ConstExp(0)),
                                                                new OpenRFile("file1","F:\\Facultate\\MAP\\ToyLanguage\\src\\readMe.txt") )))))));

        IStmt stmt3= new CmpStmt(new AssignStmt("v",new ConstExp(10)),
                new WhileStmt(new BoolExp(">",new VarExp("v"),new ConstExp(0)),
                        new CmpStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ConstExp(1)))))
        );

        IStmt stmt4=new CmpStmt(new OpenRFile("file1","F:\\Facultate\\MAP\\ToyLanguage\\src\\readMe.txt"),
                new CloseRFile(new VarExp("file1")));

        IStmt stmt5=new OpenRFile("file1","F:\\Facultate\\MAP\\ToyLanguage\\src\\readMe.txt");
                                                                                /*v=10;new(a,22);
                                                                                fork(wH(a,30);v=32;print(v);print(rH(a)));
                                                                      print(v);print(rH(a))*/

        IStmt stmt6=new CmpStmt(new AssignStmt("v",new ConstExp(10)),new CmpStmt(new NewH("a",new ConstExp(22)),
                new CmpStmt(new ForkStmt(new CmpStmt(new WriteH("a",new ConstExp(30)),
                        new CmpStmt(new AssignStmt("v",new ConstExp(32)),
                                new CmpStmt(new PrintStmt(new VarExp("v")),
                                        new PrintStmt(new ReadH("a")))))),
                        new CmpStmt(new PrintStmt(new VarExp("v")),
                                new PrintStmt(new ReadH("a"))))));

        IStmt stmt7=new CmpStmt(new AssignStmt("a",new ConstExp(1)),
                                new CmpStmt(new AssignStmt("b",new ConstExp(2)),new CmpStmt(new AssignStmt("c",new ConstExp(5)),
                                            new CmpStmt(new SwitchStmt(new ArithExp('*',new VarExp("a"),new ConstExp(10)),new ArithExp('*',new VarExp("b"),new VarExp("c")),
                                                                        new ConstExp(10),new CmpStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))),new CmpStmt(new PrintStmt(new ConstExp(100)),new PrintStmt(new ConstExp(200))),new PrintStmt(new ConstExp(300))),new PrintStmt(new ConstExp(300))))));
        IStmt test=new CmpStmt(new ForkStmt(new PrintStmt(new ConstExp(1))),new ForkStmt(new PrintStmt(new ConstExp(2))));
        IStmt infork=new CmpStmt(new Await("cnt"),
                new CmpStmt(new WriteH("v2",new ArithExp('*',new ReadH("v2"),new ConstExp(10))),
                        new CmpStmt(new WriteH("v2",new ArithExp('*',new ReadH("v2"),new ConstExp(10))),
                                new PrintStmt(new ReadH("v1")))));
        IStmt stmt8=new CmpStmt(new NewH("v1",new ConstExp(2)),
                                new CmpStmt(new NewH("v2",new ConstExp(3)),
                                            new CmpStmt(new NewH("v3",new ConstExp(4)),
                                                        new CmpStmt(new newBarrier("cnt",new ReadH("v2")),
                                                                    new CmpStmt(new ForkStmt(new CmpStmt(new Await("cnt"),
                                                                                            new CmpStmt(new WriteH("v1",new ArithExp('*',new ReadH("v1"),new ConstExp(10))),
                                                                                                                            new PrintStmt(new ReadH("v1"))))),
                                                                                new CmpStmt(new ForkStmt(infork),
                                                                                                                    new CmpStmt(new Await("cnt"),new PrintStmt(new ReadH("v3")))))))));

        states=new ArrayList<>(Arrays.asList(stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,test,stmt8));
    }

}
