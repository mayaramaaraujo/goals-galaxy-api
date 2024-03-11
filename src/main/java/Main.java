import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        int[] numeros = new int[6];
        numeros[0] = 1;
        numeros[1] = 3;
        numeros[2] = 6;
        numeros[3] = 4;
        numeros[4] = 1;
        numeros[5] = 2;


        System.out.println(solution(numeros));
    }

    public static int solution(int[] A) {
        Set<Integer> orderedList = new TreeSet<>();

        if(A.length ==0){
            return 1;
        }

        for (int i : A) {
            orderedList.add(i);
        }

        for (int i = 1; i <= orderedList.size(); i++) {
            if(!orderedList.contains(i)) {
                return i;
            }
        }

        return A.length + 1;
    }
}
