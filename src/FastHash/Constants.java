package FastHash;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class Constants {
    public static HashMap<Character, Integer> getValues() {
        return  new HashMap<Character,Integer>(){{
        put('A', 0); put('B', 1); put('C', 2); put('D', 3); put('E', 4);
        put('F', 5); put('G', 6); put('H', 7); put('I', 8); put('J', 9);
        put('K', 10);put('L', 11);put('M', 12);put('N', 13);put('Ñ', 14);
        put('O', 15);put('P', 16);put('Q', 17);put('R', 18);put('S', 19);
        put('T', 20);put('U', 21);put('V', 22);put('W', 23);put('X', 24);
        put('Y', 25);put('Z', 26);
    }};}

    public static  ArrayList<String> getLetters() {
        return new ArrayList<>(Arrays.asList(
            "A","B","C","D","E","F","G","H","I","J",
            "K","L","M","N","Ñ","O","P","Q","R","S",
            "T","U","V","W","X","Y","Z"
    ));}

    public static final int MAX_THREADS=200;    //Max threads available
}
