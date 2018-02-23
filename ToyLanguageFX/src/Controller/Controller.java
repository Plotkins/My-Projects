package Controller;

import Model.Exceptions.ADTException;
import Model.PrgState;
import Repository.IRepository;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    ExecutorService executor;

    public Controller(IRepository r) {
        repo = r;
    }

    /*private void conservativeGarbageCollector(PrgState prg) {
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, Integer> heap = state.getHeap();
        try {
            if (!heap.isEmpty() && !symTable.isEmpty())
                for (Integer i : heap.keys()) {
                    int ok = 0;
                    for (Integer j : symTable.values()) {
                        //System.out.println(i+" "+j);
                        if (i == j)
                            ok = 1;
                    }
                    if (ok == 0)
                        heap.remove(i);
                }
        } catch (ADTException e) {
            e.printStackTrace();
        }
    }*/
    public void one() throws ADTException {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        if(prgList.size()>0) {
            try {
                oneStepForAllPrg(prgList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prgList.forEach(e->{
                try {
                    repo.logPrgStateExec(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            //prgList = removeCompletedPrg(repos.getPrgList());
            executor.shutdownNow();

            repo.setPrgList(prgList);
        }
    }

    public IRepository getRepo(){return repo;}

    public void displayCurrentState(PrgState s) {
        System.out.println(s.display());
    }

    public void allStep() throws InterruptedException, ADTException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while (prgList.size() > 0) {
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();

        repo.setPrgList(prgList);
    }


    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future ->{
        try {
            return future.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }return null;}).filter(p -> p != null).collect(Collectors.toList());

        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {
                System.out.println(prg.display());
                repo.logPrgStateExec(prg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println();
        repo.setPrgList(prgList);
    }
}