package algorithms;

import java.util.ArrayList;

public class KnapSackBruteForce01 {


    public KnapSackBruteForce01(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {
        bruteForce(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);

    }

    private void bruteForce(int[] w, int[] v, int[][] dPTable, int CAPACTIY, int NUM_OF_ITEMS) {

        // do check and add

        int curWeight = 0;
        int curVal = 0;
        int finalMax = 0;
        boolean inner = false;

        // Collaborated with Parker
        //
        // Have an outer arraylist
        // make an inner arraylist of the
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 1; i <= NUM_OF_ITEMS; i++) {
            curWeight = w[i-1];
            curVal = v[i-1];
            System.out.println();
            ArrayList<Integer> in = new ArrayList<>();
            for (int j = 1; j <= CAPACTIY; j++) {
                if(j >= w[i-1]) {
                    in.add(curVal);
                    System.out.print(curVal + ", ");
                }
            }

            int outerOptValuesCombined = 0;
            int innerOptValuesCombined = 0;
            for (int k = 1, p = 1; k <= out.size(); k++, p++) {
                outerOptValuesCombined += out.get(p-1);
//                innerOptValuesCombined += in.get(k-1);
            }
            if (outerOptValuesCombined > innerOptValuesCombined) {
                System.out.println(outerOptValuesCombined);
                inner = false;
            } else {
                out = in;
                System.out.println(innerOptValuesCombined);
                inner = true;
            }

        }
//        for (int i = 0; i <= out.size()-1; i++) {
//            System.out.print(out.get(i-1));
//        }


        System.out.println("Brute Force | " + dPTable[NUM_OF_ITEMS][CAPACTIY]);



    }

}
