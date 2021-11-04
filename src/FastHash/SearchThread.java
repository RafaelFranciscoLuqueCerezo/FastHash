package FastHash;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SearchThread implements Runnable{
    private final int index;                   //Thread´s index
    private final RowHash row;                 //Row Object of the Hash
    private final ArrayList<String> object;    //String Array that contains all parameters that are going to be compare
    private final int threads;                 //Thread´s number that are going to work simultanously
    private final Semaphore sem;               //Semaphore
    private final ArrayList<Node> nodes;

    public SearchThread(int index,RowHash row,ArrayList<String> object,int threads,Semaphore sem,ArrayList<Node> nodes) {
        this.index=index;
        this.row=row;
        this.object=object;
        this.threads=threads;
        this.sem=sem;
        this.nodes=nodes;
    }

    @Override
    public void run() {
        for(int i=this.index;i<this.row.getSize();i+=threads) {
            if( isFound(i) ) {
                try {
                    this.sem.acquire(1);
                    this.nodes.add(row.getArray().get(i));
                    this.sem.release(1);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    }

    private boolean isFound(int index){
        return  row.getArray().get(index).getName().equalsIgnoreCase(object.get(0))     &&
                row.getArray().get(index).getSurName1().equalsIgnoreCase(object.get(1)) &&
                row.getArray().get(index).getSurName2().equalsIgnoreCase(object.get(2));
    }

}
