package model.algorithms;

import java.util.Date;

/**
 * @author Caleb Krauter
 * @version 1.0
 * Created as an aid for Peer 4.
 */

public class KnapSackDPUnbounded01 {

    public KnapSackDPUnbounded01(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {


        long endTime = 0;
        long totalTime = 0;
        long startTime = 0;

        Date start = new Date();
        startTime = start.getTime();

        dPBottomUpUnbounded(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);

        Date finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("KnapSack Dynamic Programming Unbounded 01 took : " + totalTime + " ms");
        System.out.println();
    }

    private void dPBottomUpUnbounded(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {
        // do check and add

        int curWeight = 0;
        int curVal = 0;
        for (int i = 1; i <= NUM_OF_ITEMS; i++) {
            curWeight = w[i-1];
            curVal = v[i-1];
            for (int j = 1; j <= CAPACTIY; j++) {

                dPTable[i][j] = dPTable[i-1][j];

                if (j >= w[i-1] && dPTable[i-1][j] < curVal + dPTable[i][j-curWeight]) {
                    dPTable[i][j] = dPTable[i][j-curWeight] + curVal;
                } else {
                    dPTable[i][j] = dPTable[i-1][j];
                }
            }
        }

        System.out.println("Bottom Up Unbounded DP | " + dPTable[NUM_OF_ITEMS][CAPACTIY]);

    }
}
