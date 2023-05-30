package controller;

//import algorithms.KnapSackBruteForce01;
import algorithms.KnapSackDP01;
import algorithms.KnapSackDPUnbounded01;
import algorithms.KnapSackEXCreditMemoized;

import java.util.Date;

/**
 * @author Caleb Krauter
 * Learned from K-wayMerge hw problem to add in timer.
 * Collaborated with Parker on 6B.
 */
public class AlgorithmsManager {
    private int[] v;
    private int[] w;
    private final int CAPACTIY = 5;
    private final int NUM_OF_ITEMS = 4;

    private int [][] dPTable;

    // TODO add methodical test cases.
    public AlgorithmsManager() {
        long endTime = 0;
        long totalTime = 0;
        long startTime = 0;


        // Knapsack dp
        Date start = new Date();
        startTime = start.getTime();

        w = new int[] {2, 1, 3, 2};
        v = new int[] {12, 10, 20, 15};
        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
        new KnapSackDP01(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);
//
        Date finish = new Date();
//        endTime = finish.getTime();
//        totalTime += (endTime - startTime);
//        System.out.println("KnapSack Dynamic Programming 01 took : " + totalTime + " ms");


        // Knapsack Unbounded
//        start = new Date();
//        startTime = start.getTime();
//        w = new int[] {2, 1, 3, 2};
//        v = new int[] {12, 10, 20, 15};
//        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
//        new KnapSackDPUnbounded01(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);

//        finish = new Date();
//        endTime = finish.getTime();
//        totalTime += (endTime - startTime);
//        System.out.println("KnapSack Dynamic Programming unbounded 01 took : " + totalTime + " ms");

        // Brute Force
//        start = new Date();
//        startTime = start.getTime();
//        w = new int[] {2, 1, 3, 2};
//        v = new int[] {12, 10, 20, 15};
//        new KnapSackBruteForce01(w, v, CAPACTIY, NUM_OF_ITEMS);
//
//        finish = new Date();
//        endTime = finish.getTime();
//        totalTime += (endTime - startTime);
//        System.out.println("KnapSack Brute Force 01 took : " + totalTime + " ms");

//        // Memmoized EX credit
//        start = new Date();
//        startTime = start.getTime();
//        w = new int[] {2, 1, 3, 2};
//        v = new int[] {12, 10, 20, 15};
//        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
//        new KnapSackEXCreditMemoized(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);
//
//        finish = new Date();
//        endTime = finish.getTime();
//        totalTime += (endTime - startTime);
//        System.out.println("KnapSack Memoized 01 took : " + totalTime + " ms");
    }
}
