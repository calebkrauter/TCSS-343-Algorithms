package controller;

//import model.algorithms.KnapSackBruteForce01;
import model.RandomNumberGenerator;
import model.algorithms.Knapsack_memoized_dp;
import model.algorithms.Knapsack_bottom_up_dp;
import model.algorithms.Knapsack_brute_force;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Caleb Krauter
 * @version 1.0
 * Collaborated with Parker on 6B.
 */
public class AlgorithmsManager {
    private int[] v;
    private int[] w;
    private int myCapacity;
    private int myNumOfItems;
    private int myNumOfItemsBound;
    private int myNumOfItemsOrigin;
    private int myCapacityBound;
    private int myCapacityOrigin;

    private int [][] dPTable;
    static int[] myWeights;
    static int[] myValues;
    RandomNumberGenerator randomNum;
    int weightOrigin;
    int weightBound;
    int valOrigin;
    int valBound;

    // TODO add methodical test cases.

    // Generate random value for the num of items, the integer used for each weight and value. pass in origin and bound. Have it return a value
    public AlgorithmsManager() {
        long endTime = 0;
        long totalTime = 0;
        long startTime = 0;
        randomNum = new RandomNumberGenerator();

        testCase1();
        printTestData();
        // Knapsack dp
        Date start = new Date();
        startTime = start.getTime();
        dPTable = new int[myNumOfItems+1][myCapacity+1];
        new Knapsack_bottom_up_dp(myWeights, myValues, myCapacity, myNumOfItems, dPTable);

        Date finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("Knapsack_bottom_up_dp took : " + totalTime + " ms");
        System.out.println();


        // Brute Force
        start = new Date();
        startTime = start.getTime();
        ArrayList<int[]> items = new ArrayList<>();
        for (int i = 0; i < myNumOfItems; i++) {
            items.add(new int[]{myWeights[i], myValues[i]});
        }
        new Knapsack_brute_force(items, myCapacity, myNumOfItems);

        finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("Knapsack_brute_force took : " + totalTime + " ms");
        System.out.println();

//        // Memmoized EX credit
        start = new Date();
        startTime = start.getTime();
        dPTable = new int[myNumOfItems+1][myCapacity+1];
        new Knapsack_memoized_dp(myWeights, myValues, myCapacity, myNumOfItems, dPTable);

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

    private void testCase1() {
        myNumOfItemsOrigin = 1;
        myNumOfItemsBound = 5;

        // default to a minimum of 1
        myCapacityOrigin = 1;
        myCapacityBound = 10;
        weightOrigin = 1;
        weightBound = 5;
        valOrigin = 1;
        valBound = 15;

        randomNum.setTheNumOfItems(myNumOfItemsOrigin, myNumOfItemsBound);
        myWeights = new int[randomNum.getTheNumOfItems()];
        myValues = new int[randomNum.getTheNumOfItems()];
        myNumOfItems = randomNum.getTheNumOfItems();

        // Get random weights and values.
        for (int i = 0; i < myNumOfItems; i++) {
            myWeights[i] = new RandomNumberGenerator().randomNumberGenerator(weightOrigin, weightBound);
            myValues[i] = new RandomNumberGenerator().randomNumberGenerator(valOrigin, valBound);
        }

        int[] sortedWeights = Arrays.stream(myWeights).sorted().toArray();
        // update to a minimum value of the minimum weight size
        myCapacityOrigin = sortedWeights[0];
        myCapacity = new RandomNumberGenerator().randomNumberGenerator(myCapacityOrigin, myCapacityBound);

    }

    private void printTestData() {
        System.out.print("Num of items | " + myNumOfItems + " | with an origin: " + myNumOfItemsOrigin + " and a bound: " + myNumOfItemsBound);
        System.out.println();
        System.out.print("Capacity | " + myCapacity + " | with an origin: " + myCapacityOrigin + " and a bound: " + myCapacityBound);
        System.out.println();
        System.out.print("Weights | ");
        for (int i = 0; i < myNumOfItems; i++) {
           System.out.print(myWeights[i] + ", ");
        }
        System.out.print("| with an origin: " + weightOrigin + " and a bound: " + weightBound);
        System.out.println();
        System.out.print("Values | ");
        for (int i = 0; i < myNumOfItems; i++) {
            System.out.print(myValues[i] + ", ");
        }
        System.out.print("| with an origin: " + valOrigin + " and a bound: " + valBound);
        System.out.println();
        System.out.println();
    }

}
