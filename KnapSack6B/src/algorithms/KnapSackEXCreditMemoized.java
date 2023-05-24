package algorithms;

public class KnapSackEXCreditMemoized {
    int[] w;
    int[] v;
    int[][] dPTable;
    int CAPACTIY;
    int NUM_OF_ITEMS;
    public KnapSackEXCreditMemoized(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {
        this.w = w;
        this.v = v;
        this.dPTable = dPTable;
        this.CAPACTIY = CAPACTIY;
        this.NUM_OF_ITEMS = NUM_OF_ITEMS;
        int i = NUM_OF_ITEMS;
        int j = CAPACTIY;
        initDPTable();
        System.out.println("Top Down Memoized | " + dPMemoized(i, j));
        //        printTable();
    }

    private void initDPTable() {

        for (int i = 0; i <= this.NUM_OF_ITEMS; i++) {
//            System.out.println();
            for (int j = 0; j <= this.CAPACTIY; j++ ) {
                    dPTable[i][j] = -1;
//                    System.out.print(dPTable[i][j] + ", ");

            }
        }
//        System.out.println();
//        System.out.println();

    }



    private int dPMemoized(int i, int j) {
       if (i == 0 || j == 0) {
           return 0;
       }
       if (dPTable[i][j] != -1) {
           return dPTable[i][j];
       }
        int val = 0;
       if (dPTable[i][j] < 0) {
           if (j < w[i-1]) {
               val = dPMemoized(i-1, j);
           } else {
               if (dPMemoized(i-1, j) < v[i-1] + dPMemoized(i-1, j-w[i-1])) {
                   val = v[i-1] + dPMemoized(i-1, j-w[i-1]);
               } else {
                   val = dPMemoized(i-1, j);
               }
           }
       }
       dPTable[i][j] = val;
       return dPTable[i][j];
    }

//    private void printTable() {
//        for (int i = 0; i <= NUM_OF_ITEMS; i++) {
//            System.out.println();
//
//            for (int j = 0; j <= CAPACTIY; j++ ) {
//                if (i > 0 && j > 0) {
//                    System.out.print(dPTable[i][j] + ", ");
//                } else {
//                    System.out.print(dPTable[i][j] + " , ");
//                }
//
//            }
//
//        }
//    }
}
