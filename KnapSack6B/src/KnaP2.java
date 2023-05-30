import javax.print.DocFlavor;
import java.util.ArrayList;

class Main2 {
    public static void main(String[] args) {
        new KnaP2();
    }
}

public class KnaP2 {

    // TODO Create an outer arraylist and add to it the index of each included item.
    ArrayList<Integer> optimalSetOfIndecies;
    public KnaP2() {
        optimalSetOfIndecies = new ArrayList<>();
        final int C = 5;
        final int N = 4;
        int[] w = new int[] {2, 1, 3, 3};
        int[] v = new int[] {12, 10, 20, 15};
        System.out.println(getMaxV(N, w, v, C, 0));
        for (int i = 0; i < optimalSetOfIndecies.size(); i++) {

            System.out.print(optimalSetOfIndecies.toArray()[i] + ", ");
        }
    }

    private int getMaxV(int n, int[] w, int[] v, int c, int i) {
        int vInclude;
        int vExclude;
        if (i == n || c <= 0) {
            return 0;
        }


        if (w[i] <= c) {

            vInclude = getMaxV(n, w, v, c-w[i], i+1) + v[i];
            vExclude = getMaxV(n, w, v, c, i+1);
            return Math.max(vInclude, vExclude);
        } else {
            vExclude = getMaxV(n, w, v, c, i+1);
            return vExclude;
        }
    }

}
