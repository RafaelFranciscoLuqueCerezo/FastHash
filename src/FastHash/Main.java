package FastHash;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        FastHash t_hash=new FastHash(5);
        System.out.println("rellenado vector");
        for(int i=0;i<10000000;i++) {
            int val1 = getRandomNumber(0,26);
            int val2 = getRandomNumber(0,26);
            int val3 = getRandomNumber(0,26);
            t_hash.addNodo(new Node(
                    Constants.getLetters().get(val1)+Constants.getLetters().get(val2),
                 Constants.getLetters().get(val2)+Constants.getLetters().get(val3),
                          Constants.getLetters().get(val3)));
        }
        System.out.println("fin relleno");
        t_hash.addNodo(new Node("Antonio","Aranda","Aguilar").setDni("31028144-V"));
        t_hash.addNodo(new Node("Antonio","Aranda","Aguilar").setDni("31254155-B"));
        ArrayList<Node> nodes=t_hash.findNode("Antonio","Aranda","Aguilar");

        System.out.println(nodes.size());
        nodes.forEach(element-> System.out.println(element.getNodeToString()));

    }

    private static int getRandomNumber(int min, int max){
        return (int) Math.floor((Math.random()* (max - min)) + min);
    }

}
