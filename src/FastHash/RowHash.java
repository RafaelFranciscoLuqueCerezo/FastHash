package FastHash;

import java.util.ArrayList;

public class RowHash {
    private final ArrayList<Node> InnerArray=new ArrayList<>();
    public void clearArray() {this.InnerArray.clear();}
    public int  getSize() {return this.InnerArray.size();}
    public ArrayList<Node> getArray(){return this.InnerArray;}
    public void addNode(Node node) {this.InnerArray.add(node);}
}
