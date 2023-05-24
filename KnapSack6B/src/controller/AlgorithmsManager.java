package controller;

import algorithms.KnapSackBruteForce01;
import algorithms.KnapSackDP01;
import algorithms.KnapSackDPUnbounded01;
import algorithms.KnapSackEXCreditMemoized;

public class AlgorithmsManager {
    private int[] v;
    private int[] w;
    private final int CAPACTIY = 5;
    private final int NUM_OF_ITEMS = 4;

    private int [][] dPTable;
    public AlgorithmsManager() {
        w = new int[] {2, 1, 3, 2};
        v = new int[] {12, 10, 20, 15};
        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
        new KnapSackDP01(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);
        w = new int[] {2, 1, 3, 2};
        v = new int[] {12, 10, 20, 15};
        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
        new KnapSackDPUnbounded01(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);
        w = new int[] {2, 1, 3, 2};
        v = new int[] {12, 10, 20, 15};
        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
        new KnapSackBruteForce01(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);
        w = new int[] {2, 1, 3, 2};
        v = new int[] {12, 10, 20, 15};
        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
        new KnapSackEXCreditMemoized(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);
    }
}
