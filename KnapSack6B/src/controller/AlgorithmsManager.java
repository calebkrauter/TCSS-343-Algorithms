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
 * Collaborated with Parker and Anthony on 6B.
 */
public class AlgorithmsManager {


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
    int myNumOfIterations = 3;
    ArrayList<int[]> items;

    // TODO add methodical test cases.

    // Generate random value for the num of items, the integer used for each weight and value. pass in origin and bound. Have it return a value
    public AlgorithmsManager() {
        long endTime = 0;
        long totalTime = 0;
        long startTime = 0;
        randomNum = new RandomNumberGenerator();

        // The following four test cases are for testing Bottom-Up, Memoized and Bruteforce and comparing them.
        // Larger tests are done on 5 through 9 for Bottom-up and Memoized.
        // default to a minimum capacity of 1
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 1;
        myNumOfItemsBound = 5;
        myCapacityOrigin = 1;
        myCapacityBound = 10;
        weightOrigin = 1;
        weightBound = 5;
        valOrigin = 1;
        valBound = 15;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 5;
        myNumOfItemsBound = 10;
        myCapacityOrigin = 1;
        myCapacityBound = 50;
        weightOrigin = 15;
        weightBound = 75;
        valOrigin = 15;
        valBound = 99;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runAlgorithms();



        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 3 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 10;
        myNumOfItemsBound = 15;
        myCapacityOrigin = 1;
        myCapacityBound = 500;
        weightOrigin = 50;
        weightBound = 900;
        valOrigin = 50;
        valBound = 150;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 4 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 15;
        myNumOfItemsBound = 20;
        myCapacityOrigin = 1;
        myCapacityBound = 5;
        weightOrigin = 1;
        weightBound = 900;
        valOrigin = 50;
        valBound = 100;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 4 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 20;
        myNumOfItemsBound = 25;
        myCapacityOrigin = 1000;
        myCapacityBound = 2000;
        weightOrigin = 1000;
        weightBound = 1500;
        valOrigin = 100;
        valBound = 300;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runAlgorithms();

        // The dynamic programming algorithms are compared here.
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 5 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 49;
        myNumOfItemsBound = 50;
        myCapacityOrigin = 500;
        myCapacityBound = 1000;
        weightOrigin = 5;
        weightBound = 50;
        valOrigin = 100;
        valBound = 300;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runDPAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 6 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 100;
        myNumOfItemsBound = 500;
        myCapacityOrigin = 1000;
        myCapacityBound = 5000;
        weightOrigin = 500;
        weightBound = 1000;
        valOrigin = 500;
        valBound = 1000;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runDPAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 7 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 500;
        myNumOfItemsBound = 1000;
        myCapacityOrigin = 2000;
        myCapacityBound = 10000;
        weightOrigin = 1000;
        weightBound = 2000;
        valOrigin = 1000;
        valBound = 2000;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runDPAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 8 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 1000;
        myNumOfItemsBound = 2000;
        myCapacityOrigin = 5000;
        myCapacityBound = 12000;
        weightOrigin = 5000;
        weightBound = 10000;
        valOrigin = 1500;
        valBound = 5000;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runDPAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 9 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 5000;
        myNumOfItemsBound = 10000;
        myCapacityOrigin = 10000;
        myCapacityBound = 15000;
        weightOrigin = 10000;
        weightBound = 15000;
        valOrigin = 5000;
        valBound = 10000;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, weightOrigin, weightBound, valOrigin, valBound);
        printTestData();
        runDPAlgorithms();
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

    private void runAlgorithms() {

        items = new ArrayList<>();
        for (int i = 0; i < myNumOfItems; i++) {
            items.add(new int[]{myWeights[i], myValues[i]});
        }

        // Init table for dynamic programming solutions
        dPTable = new int[myNumOfItems+1][myCapacity+1];

        for (int i = 0; i < myNumOfIterations; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_brute_force(items, myCapacity, myNumOfItems);
        }
        for (int i = 0; i < myNumOfIterations; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_bottom_up_dp(myWeights, myValues, myCapacity, myNumOfItems, dPTable);
        }

        for (int i = 0; i < myNumOfIterations; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_memoized_dp(myWeights, myValues, myCapacity, myNumOfItems, dPTable);
        }

    }

    private void runDPAlgorithms() {

        // Init table for dynamic programming solutions
        dPTable = new int[myNumOfItems+1][myCapacity+1];
        for (int i = 0; i < myNumOfIterations; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_bottom_up_dp(myWeights, myValues, myCapacity, myNumOfItems, dPTable);
        }

        for (int i = 0; i < myNumOfIterations; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_memoized_dp(myWeights, myValues, myCapacity, myNumOfItems, dPTable);
        }
    }
    private void setupTestCase(int theNumOfItemsOrigin, int theNumOfItemsBound, int theCapacityOrigin, int theCapacityBound, int theWeightOrigin, int theWeightBound, int theValOrigin, int theValBound) {
        myNumOfItemsOrigin = theNumOfItemsOrigin;
        myNumOfItemsBound = theNumOfItemsBound;

        // default to a minimum capacity of 1
        myCapacityOrigin = theCapacityOrigin;
        myCapacityBound = theCapacityBound;
        weightOrigin = theWeightOrigin;
        weightBound = theWeightBound;
        valOrigin = theValOrigin;
        valBound = theValBound;

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
        if (myCapacityOrigin >= myCapacityBound) {
            myCapacityOrigin = sortedWeights[0];
            myCapacityBound = sortedWeights[sortedWeights.length-1];
        }
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
