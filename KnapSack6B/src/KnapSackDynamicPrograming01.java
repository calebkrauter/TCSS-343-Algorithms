public class KnapSackDynamicPrograming01 {

    private int[] v;
    private int[] w;
    private final int CAPACTIY = 5;
    private final int NUM_OF_ITEMS = 4;

    private int [][] dPTable;

    public KnapSackDynamicPrograming01() {
        w = new int[] {2, 1, 3, 2};
        v = new int[] {12, 10, 20, 15};
        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
        dPBottomUp();

    }

    private void dPBottomUp() {
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

        System.out.println(dPTable[NUM_OF_ITEMS][CAPACTIY]);

    }

}
