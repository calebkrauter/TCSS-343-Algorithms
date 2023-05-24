package algorithms;

public class KnapSackBruteForce01 {


    public KnapSackBruteForce01(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {
        bruteForce(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);

    }

    private void bruteForce(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {

        // do check and add

        int[] arrayOfValues = new int[CAPACTIY * NUM_OF_ITEMS];
        int curWeight = 0;
        int curVal = 0;
        int k = 0;

        // Collaborated with Parker
        //
        // Have an outer arraylist
        // make an inner arraylist of the

        for (int i = 1; i <= NUM_OF_ITEMS; i++) {
            curWeight = w[i-1];
            curVal = v[i-1];
            for (int j = 1; j <= CAPACTIY; j++) {
                k++;

            }
        }

        System.out.println("Brute Force | " + dPTable[NUM_OF_ITEMS][CAPACTIY]);


    }

}
