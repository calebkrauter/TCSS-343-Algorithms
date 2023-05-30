package controller;

//import algorithms.KnapSackBruteForce01;
import algorithms_model.Knapsack_memoized_dp;
import algorithms_model.Knapsack_bottom_up_dp;
import algorithms_model.Knapsack_brute_force;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Caleb Krauter
 * @version 1.0
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
//
        w = new int[] {2, 1, 3, 2};
        v = new int[] {12, 10, 20, 15};
        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
        new Knapsack_bottom_up_dp(w, v, CAPACTIY, NUM_OF_ITEMS, dPTable);
//
        Date finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("Knapsack_bottom_up_dp took : " + totalTime + " ms");
        System.out.println();


        // Brute Force
        start = new Date();
        startTime = start.getTime();
        int capacity = 5;
        ArrayList<int[]> items = new ArrayList<>();
        items.add(new int[]{2, 12});
        items.add(new int[]{1, 10});
        items.add(new int[]{3, 20});
        items.add(new int[]{2, 15});
        new Knapsack_brute_force(items, capacity, items.size());

        finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("Knapsack_brute_force took : " + totalTime + " ms");
        System.out.println();

//        // Memmoized EX credit
        start = new Date();
        startTime = start.getTime();
        w = new int[] {2, 1, 3, 2};
        v = new int[] {12, 10, 20, 15};
        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
        new Knapsack_memoized_dp(w, v, CAPACTIY, NUM_OF_ITEMS, dPTable);

        finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("knapsack-memoized-dp took : " + totalTime + " ms");


        // Uncomment to see knapsack unbounded in action.
        // Knapsack Unbounded
//        start = new Date();
//        startTime = start.getTime();
//        w = new int[] {2, 1, 3, 2};
//        v = new int[] {12, 10, 20, 15};
//        dPTable = new int[NUM_OF_ITEMS+1][CAPACTIY+1];
//        new KnapSackDPUnbounded01(w, v, dPTable, CAPACTIY, NUM_OF_ITEMS);
//
//        finish = new Date();
//        endTime = finish.getTime();
//        totalTime += (endTime - startTime);
//        System.out.println("KnapSack Dynamic Programming unbounded 01 took : " + totalTime + " ms");
//        System.out.println();
    }
}
