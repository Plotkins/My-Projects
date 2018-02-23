package Model;

import java.util.List;

public class BarrierTuple {
    public int id;
    public int barrierLength;
    public List<Integer> barrierPool;

    public BarrierTuple(int i,int bL,List<Integer> pool){
        this.id=i;
        this.barrierLength=bL;
        this.barrierPool=pool;
    }

    public String poolToString(){
        String result="";
        for(Integer i:barrierPool){
            result+=i+", ";
        }
        System.out.println(result);
        return result;
    }
}
