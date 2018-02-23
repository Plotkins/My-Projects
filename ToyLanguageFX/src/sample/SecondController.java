package sample;

import Controller.Controller;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.BarrierTuple;
import Model.Exceptions.ADTException;
import Model.FileTuple;
import Model.PrgState;
import Model.Statements.IStmt;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;

import java.util.*;

public class SecondController implements Initializable {

    @FXML
    private ListView<String> exeListView;

    @FXML
    private ListView<Integer> idListView;

    @FXML
    private TableView<Map.Entry<String, Integer>> symTableView;
    @FXML
    private TableColumn<Map.Entry<String,Integer>,String> STName;
    @FXML
    private TableColumn<Map.Entry<String,Integer>,Integer> STValue;

    @FXML
    private TableView<Map.Entry<Integer,Integer>> heapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer,Integer>,Integer> HTAdress;
    @FXML
    private TableColumn<Map.Entry<Integer,Integer>,Integer> HTValue;

    @FXML
    private TableView<Map.Entry<Integer,String>> fileTableView;
    @FXML
    private TableColumn<Map.Entry<Integer,String>,Integer> FTValue;
    @FXML
    private TableColumn<Map.Entry<Integer,String>,String> FTPath;

    @FXML
    private TableView<BarrierTuple> barrierTableView;
    @FXML
    private TableColumn<BarrierTuple,Integer> BTIndex;
    @FXML
    private TableColumn<BarrierTuple,Integer> BTValue;
    @FXML
    private TableColumn<BarrierTuple,String> BTList;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private Button runButton;

    @FXML
    private TextField nrOfThreadsLine;

    @FXML
    private TextField currentLine;

    public Controller ctrl1;
    private List<Integer> lista1=new ArrayList<>();
    private PrgState prg1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrl1=MainController.ctrl;

        HTAdress.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        HTValue.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        FTValue.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        FTPath.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue() + ""));

        STName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        STValue.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        BTIndex.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().id).asObject());
        BTValue.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().barrierLength).asObject());
        BTList.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().barrierPool.toString()));


        try {
            prg1 = ctrl1.getRepo().getPrgList().get(0);
            changeProgramState(prg1);
        }catch (ADTException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void executeOneStep() throws ADTException {
        int size=ctrl1.getRepo().getPrgList().size();
        boolean programStateLeft = ctrl1.getRepo().getPrgList().get(size-1).getExeStack().isEmpty();
        if(programStateLeft){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        ctrl1.one();
        ctrl1.removeCompletedPrg(ctrl1.getRepo().getPrgList());
        size=ctrl1.getRepo().getPrgList().size();
        if (prg1.getExeStack().isEmpty() && size>1){
            prg1=ctrl1.getRepo().getProgramStateWithId(prg1.getId()*10);
        }
        //prgg=ctr.getRepo().getPrgList().get(sz-1);
        changeProgramState(prg1);
    }

    private void changeProgramState(PrgState prg1) throws ADTException {
        if(prg1 == null)
            return;
        update_Stack(prg1);
        update_SymTable(prg1);
        update_file(prg1);
        update_heap(prg1);
        update_out(prg1);
        update_ids();
        update_current_id();
        update_nr_of_prg();
        update_barrier(prg1);
    }

    private void update_nr_of_prg() {
        try{
            int size=ctrl1.getRepo().getPrgList().size();
            nrOfThreadsLine.setText("Number of threads : "+size);
        } catch (ADTException e) {
            e.printStackTrace();
        }
    }

    private void update_current_id() {
        try{
        int size=ctrl1.getRepo().getPrgList().size();
        currentLine.setText("Current ID: "+ctrl1.getRepo().getPrgList().get(size-1).getId());
        } catch (ADTException e) {
            e.printStackTrace();
        }
    }

    private void update_ids() {
        try{
        lista1.clear();
        List<PrgState> states=ctrl1.getRepo().getPrgList();
        for(PrgState pr : states)
            lista1.add(pr.getId());
        idListView.setItems(FXCollections.observableList(lista1));
        }catch (ADTException e){
            System.out.println(e.getMessage());
        }
    }

    private void update_out(PrgState prg) {
        MyIList<Integer> out=prg.getOut();
        List<String> o=new ArrayList<>();
        out.getAll().forEach(e->o.add(e.toString()));
        outputListView.setItems(FXCollections.observableList(o));
        outputListView.refresh();
    }

    private void update_heap(PrgState prg) {
        MyIDictionary<Integer,Integer> heap=prg.getHeap();
        List<Map.Entry<Integer,Integer>> lista=new ArrayList<>();
        for(Map.Entry<Integer,Integer>entry:heap.getAll()){
            lista.add(entry);
        }
        heapTableView.setItems(FXCollections.observableList(lista));
        heapTableView.refresh();
    }

    private void update_file(PrgState prg) {
        MyIDictionary<Integer, FileTuple> symtbl=prg.getFileTable();
        Map<Integer, String> lista = new HashMap<>();
        for(Map.Entry<Integer, FileTuple> entry: symtbl.getAll())
        {
            lista.put(entry.getKey(),entry.getValue().fileName);
        }
        List<Map.Entry<Integer,String>> l2=new ArrayList<>(lista.entrySet());
        fileTableView.setItems(FXCollections.observableList(l2));
        fileTableView.refresh();
    }

    private void update_barrier(PrgState prg) throws ADTException {
        MyIDictionary<Integer,BarrierTuple> barr=prg.getBarrierTable();
        List<BarrierTuple> list=new ArrayList<>();
        for(BarrierTuple b:barr.values())
            list.add(b);
        barrierTableView.setItems(FXCollections.observableList(list));
    }

    private void update_SymTable(PrgState prg) {
        MyIDictionary<String,Integer> symtbl=prg.getSymTable();
        List<Map.Entry<String,Integer>> lista=new ArrayList<>();
        for(Map.Entry<String,Integer> entry: symtbl.getAll())
        {
            lista.add(entry);
        }
        ObservableList<Map.Entry<String, Integer>> items=FXCollections.observableList(lista);
        symTableView.setItems(items);
        symTableView.refresh();
    }

    private void update_Stack(PrgState current) {
        MyIStack<IStmt> mystack=current.getExeStack();
        List<String> execution=new ArrayList<>();
        List<IStmt> stack=mystack.getStack();
        stack.forEach(e->execution.add(e.toString()));

        exeListView.setItems(FXCollections.observableList(execution));
    }
    public void up_ind() throws ADTException {

        int index = idListView.getSelectionModel().getSelectedIndex();
        if(index < 0)
            index=0;
        int idx=lista1.get(index);
        prg1=ctrl1.getRepo().getProgramStateWithId(idx);
        changeProgramState(prg1);

    }
}
