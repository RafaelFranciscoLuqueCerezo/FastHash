package FastHash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class FastHash {
    private final int space;                                          //Space´s number that the algorithm is going to consider
    private final RowHash[] HashArray;                                //Hash array
    private final Semaphore sem = new Semaphore(1);            //Semaphore to control concurrency
    private final ArrayList<Node> nodes = new ArrayList<>();          //Nodes found by findNode method

    public FastHash(int letters) {
        this.space = letters < 3 ? 3 : Math.min(letters, 6);
        int num = (int) Math.pow(27, this.space);
        System.out.println("generando hash de (" + num + ") elementos");
        this.HashArray = new RowHash[num];
    }

    //findNodo    : found all nodes that have same name, first surname and second surname
    public ArrayList<Node> findNode(String name, String surname1, String surname2) {
        long timerStart = System.currentTimeMillis();
        ArrayList<String> object = new ArrayList<>(Arrays.asList(name, surname1, surname2));
        int memoryPosition = foundMemoryPositionHash(object);
        nodes.clear();
        if (this.HashArray[memoryPosition] != null) {
            int threads = Math.min(this.HashArray[memoryPosition].getSize(), Constants.MAX_THREADS);
            ExecutorService executor = Executors.newFixedThreadPool(threads);
            for (int i = 0; i < threads; i++) {
                executor.execute(new SearchThread(i, this.HashArray[memoryPosition], object, threads, this.sem, this.nodes));
            }
            executor.shutdown();
            while (!executor.isTerminated()) ;
        }
        long timerEnd = System.currentTimeMillis();
        System.out.println("findNodo() tiempo(milisegundos): " + (timerEnd - timerStart) + " ms");
        return nodes;
    }

    //addNodo     : add a node to the hash array using a concrete memory position
    public void addNodo(Node node) {
        int memoryPosition = foundMemoryPositionHash(node.getArrayString());
        if (this.HashArray[memoryPosition] == null) this.HashArray[memoryPosition] = new RowHash();
        this.HashArray[memoryPosition].addNode(node);
    }

    //foundMemoryPositionHash : generate a memory position due to an algorithm
    public int foundMemoryPositionHash(ArrayList<String> FullName) {
        ArrayList<Character> characters = new ArrayList<>();
        this.fillValuesWithFullName(FullName, characters);
        return IntStream.range(0, characters.size())
                .reduce((acc, index) -> acc += Constants.getValues().get(characters.get(index)) * (int) Math.pow(27, index))
                .getAsInt();
    }
    //fillValuesWithFullName: fill an array list of character due to a determinate algorithm
    private void fillValuesWithFullName(ArrayList<String> fullName, ArrayList<Character> values) {
        for (int spaceIndex = 0, fullNameIndex = 0; spaceIndex < this.space; spaceIndex++, fullNameIndex++) {
            values.add(fullName.get(fullNameIndex).toUpperCase().charAt(0));
            if (this.space - spaceIndex - 1 > 1) {
                values.add(fullName.get(fullNameIndex).toUpperCase().charAt(1));
                spaceIndex++;
            }
        }
    }
}
