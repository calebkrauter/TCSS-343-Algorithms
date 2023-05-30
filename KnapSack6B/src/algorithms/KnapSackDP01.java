package algorithms;

import java.util.ArrayList;

/**
 * @author Caleb Krauter
 */
public class KnapSackDP01 {

    public KnapSackDP01(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {

        dPBottomUp(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);

    }

    // TOdo implement backtracking correctly.
    private void dPBottomUp(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {
        // do check and add


        int curWeight = 0;
        int curVal = 0;
        ArrayList<Integer> optimalSetOfIndices;

        for (int i = 1; i <= NUM_OF_ITEMS; i++) {
            curWeight = w[i-1];
            curVal = v[i-1];

            for (int j = 1; j <= CAPACTIY; j++) {

                dPTable[i][j] = dPTable[i-1][j];

                if (j >= w[i-1] && dPTable[i-1][j] < curVal + dPTable[i-1][j-curWeight]) {
                    dPTable[i][j] = dPTable[i-1][j-curWeight] + curVal;
                } else {
                    dPTable[i][j] = dPTable[i-1][j];
                }
            }
        }
        int j = CAPACTIY;

        optimalSetOfIndices = new ArrayList<>();
        for (int i = dPTable.length - 1; i > 0; i--) {
            if (dPTable[i][j] != dPTable[i-1][j]) {
                optimalSetOfIndices.add(i-1);
                j -= w[i-1];

            }
        }

        System.out.print("[");
        for (int i = optimalSetOfIndices.size() - 1; i >= 0; i--) {
            System.out.print("index: " + optimalSetOfIndices.get(i) + " ");
        }
        System.out.print("]");
        System.out.println();


        System.out.println("Bottom Up DP | " + dPTable[NUM_OF_ITEMS][CAPACTIY]);

    }

}
