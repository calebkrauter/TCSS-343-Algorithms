package algorithms;

public class KnapSackDP01 {

    public KnapSackDP01(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {

        dPBottomUp(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);

    }

    private void dPBottomUp(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {
        // do check and add


        int curWeight = 0;
        int curVal = 0;
        for (int i = 1; i <= NUM_OF_ITEMS; i++) {
            curWeight = w[i-1];
            curVal = v[i-1];
            for (int j = 1; j <= CAPACTIY; j++) {

                dPTable[i][j] = dPTable[i-1][j];
//                System.out.print(j + " ");
//                System.out.print(w[i] + " ");
                if (j >= w[i-1] && dPTable[i-1][j] < curVal + dPTable[i-1][j-curWeight]) {
                    dPTable[i][j] = dPTable[i-1][j-curWeight] + curVal;
                } else {
                    dPTable[i][j] = dPTable[i-1][j];
                }
            }
        }

        System.out.println("Bottom Up DP | " + dPTable[NUM_OF_ITEMS][CAPACTIY]);

    }

}
