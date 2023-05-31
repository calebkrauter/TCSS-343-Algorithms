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

/**
 * Manages test cases and algorithms.
 */
public class AlgorithmsManager {
    /**
     * Capacity.
     */
    private int W;
    /**
     * Number of myItems.
     */
    private int n;
    /**
     * The number of myItems bound used for generating a random number.
     */
    private int myNumOfItemsBound;
    /**
     * The number of myItems origin used for generating a random number.
     */
    private int myNumOfItemsOrigin;
    /**
     * The number for capacity bound used for generating a random number.
     */
    private int myCapacityBound;
    /**
     * The number for capacity origin used for generating a random number.
     */
    private int myCapacityOrigin;
    /**
     * myDPTable is a 2D array that holds the integers representing values
     * for each 'i' item at each 'j' capacity.
     */
    private int [][] myDPTable;
    /**
     * An array of weights each of which correspond to each item respectively.
     */
    private static int[] weights;
    /**
     * An array of values each of which correspond to each item respectively.
     */
    private static int[] values;
    /**
     * A reference to the random number generator Object.
     */
    private RandomNumberGenerator myRandomNum;
    /**
     * The number for weight origin used for generating a random number.
     */
    private int myWeightOrigin;
    /**
     * The number for weight bound used for generating a random number.
     */
    private int myWeightBound;
    /**
     * The number for value origin used for generating a random number.
     */
    private int myValOrigin;
    /**
     * The number for value bound used for generating a random number.
     */
    private int myValBound;
    /**
     * A class constant used for the number of times each algorithm is run for experimentation purposes.
     */
    private final int NUM_OF_ITERATIONS = 3;
    /**
     * An arraylist used to contain myItems' weights and values for the bruteforce algorithm.
     */
    private ArrayList<int[]> myItems;

    // Generate random value for the num of myItems, the integer used for each weight and value. pass in origin and bound. Have it return a value
    public AlgorithmsManager() {
        myRandomNum = new RandomNumberGenerator();

        // For cases 1 through 5.
        // Increase item-number origin and bound by 5 every test
        // Increase capacity, weight and value origin and bound by a multiple of 5 each time.

        // The following four test cases are for testing Bottom-Up, Memoized and Bruteforce and comparing them.
        // Larger tests are done on 5 through 10 for Bottom-up and Memoized.
        // default to a minimum capacity of 1
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        myNumOfItemsBound = 5;
        myNumOfItemsBound = 10;
        myWeightOrigin = 1;
        myWeightBound = 10;
        myValOrigin = 1;
        myValBound = 10;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 5;
        myNumOfItemsBound = 10;
        myCapacityOrigin = 5;
        myCapacityBound = 50;
        myWeightOrigin = 5;
        myWeightBound = 50;
        myValOrigin = 5;
        myValBound = 50;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runAlgorithms();



        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 3 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 10;
        myNumOfItemsBound = 15;
        myCapacityOrigin = 25;
        myCapacityBound = 150;
        myWeightOrigin = 25;
        myWeightBound = 150;
        myValOrigin = 25;
        myValBound = 250;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 4 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 15;
        myNumOfItemsBound = 20;
        myCapacityOrigin = 125;
        myCapacityBound = 750;
        myWeightOrigin = 125;
        myWeightBound = 750;
        myValOrigin = 125;
        myValBound = 1250;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 5 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 20;
        myNumOfItemsBound = 25;
        myCapacityOrigin = 625;
        myCapacityBound = 3750;
        myWeightOrigin = 625;
        myWeightBound = 3750;
        myValOrigin = 625;
        myValBound = 6250;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runAlgorithms();

        // The dynamic programming algorithms are compared here.
        // For cases 6 through 10.
        // Increase item-number, capacity, weight and value origin and bound by a multiple of 5 each time.
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 6 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 5;
        myNumOfItemsBound = 10;
        myCapacityOrigin = 100;
        myCapacityBound = 200;
        myWeightOrigin = 1;
        myWeightBound = 10;
        myValOrigin = 20;
        myValBound = 60;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runDPAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 7 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 30;
        myNumOfItemsBound = 50;
        myCapacityOrigin = 500;
        myCapacityBound = 1000;
        myWeightOrigin = 5;
        myWeightBound = 50;
        myValOrigin = 100;
        myValBound = 300;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runDPAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 8 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 150;
        myNumOfItemsBound = 250;
        myCapacityOrigin = 2500;
        myCapacityBound = 5000;
        myWeightOrigin = 25;
        myWeightBound = 250;
        myValOrigin = 500;
        myValBound = 1500;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runDPAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 9 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 750;
        myNumOfItemsBound = 1250;
        myCapacityOrigin = 12500;
        myCapacityBound = 25000;
        myWeightOrigin = 125;
        myWeightBound = 1250;
        myValOrigin = 2500;
        myValBound = 7500;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runDPAlgorithms();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST CASE 10 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        myNumOfItemsOrigin = 3750;
        myNumOfItemsBound = 6250;
        myCapacityOrigin = 62500;
        myCapacityBound = 125000;
        myWeightOrigin = 625;
        myWeightBound = 6250;
        myValOrigin = 12500;
        myValBound = 37500;
        setupTestCase(myNumOfItemsOrigin, myNumOfItemsBound, myCapacityOrigin, myCapacityBound, myWeightOrigin, myWeightBound, myValOrigin, myValBound);
        printTestData();
        runDPAlgorithms();

    }

    /**
     * Runs the algorithms a specific number of times for experimentation.
     */
    private void runAlgorithms() {

        myItems = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            myItems.add(new int[]{weights[i], values[i]});
        }

        // Init table for dynamic programming solutions
        myDPTable = new int[n+1][W+1];

        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_brute_force(myItems, W, n);
        }
        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_bottom_up_dp(weights, values, W, n, myDPTable);
        }

        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_memoized_dp(weights, values, W, n, myDPTable);
        }

    }

    /**
     * Runs DP algorithms for experimentation.
     */
    private void runDPAlgorithms() {

        // Init table for dynamic programming solutions
        myDPTable = new int[n+1][W+1];
        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_bottom_up_dp(weights, values, W, n, myDPTable);
        }

        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            System.out.println("============Iteration " + i + " ========================");
            new Knapsack_memoized_dp(weights, values, W, n, myDPTable);
        }
    }

    /**
     * Sets up test cases for running experiments.
     * @param theNumOfItemsOrigin
     * @param theNumOfItemsBound
     * @param theCapacityOrigin
     * @param theCapacityBound
     * @param themyWeightOrigin
     * @param themyWeightBound
     * @param themyValOrigin
     * @param themyValBound
     */
    private void setupTestCase(int theNumOfItemsOrigin, int theNumOfItemsBound, int theCapacityOrigin, int theCapacityBound, int themyWeightOrigin, int themyWeightBound, int themyValOrigin, int themyValBound) {
        myNumOfItemsOrigin = theNumOfItemsOrigin;
        myNumOfItemsBound = theNumOfItemsBound;

        // default to a minimum capacity of 1
        myCapacityOrigin = theCapacityOrigin;
        myCapacityBound = theCapacityBound;
        myWeightOrigin = themyWeightOrigin;
        myWeightBound = themyWeightBound;
        myValOrigin = themyValOrigin;
        myValBound = themyValBound;

        myRandomNum.setTheNumOfItems(myNumOfItemsOrigin, myNumOfItemsBound);
        weights = new int[myRandomNum.getTheNumOfItems()];
        values = new int[myRandomNum.getTheNumOfItems()];
        n = myRandomNum.getTheNumOfItems();

        // Get random weights and values.
        for (int i = 0; i < n; i++) {
            weights[i] = new RandomNumberGenerator().randomNumberGenerator(myWeightOrigin, myWeightBound);
            values[i] = new RandomNumberGenerator().randomNumberGenerator(myValOrigin, myValBound);
        }

        int[] sortedWeights = Arrays.stream(weights).sorted().toArray();
        // update to a minimum value of the minimum weight size
        if (myCapacityOrigin >= myCapacityBound) {
            myCapacityOrigin = sortedWeights[0];
            myCapacityBound = sortedWeights[sortedWeights.length-1];
        }
        W = new RandomNumberGenerator().randomNumberGenerator(myCapacityOrigin, myCapacityBound);

    }

    /**
     * Outputs test data.
     */
    private void printTestData() {
        System.out.print("Num of items | " + n + " | with an origin: " + myNumOfItemsOrigin + " and a bound: " + myNumOfItemsBound);
        System.out.println();
        System.out.print("Capacity | " + W + " | with an origin: " + myCapacityOrigin + " and a bound: " + myCapacityBound);
        System.out.println();
        System.out.print("Weights | ");
        // Uncomment to see the generated weights.
//        for (int i = 0; i < n; i++) {
//           System.out.print(weights[i] + ", ");
//        }
        System.out.print("| with an origin: " + myWeightOrigin + " and a bound: " + myWeightBound);
        System.out.println();
        System.out.print("Values | ");
        // Uncomment to see the generated values.
//        for (int i = 0; i < n; i++) {
//            System.out.print(values[i] + ", ");
//        }
        System.out.print("| with an origin: " + myValOrigin + " and a bound: " + myValBound);
        System.out.println("There are " + n + " Items and thus " + n + " number of randomly generated weights and values");
        System.out.println();
        System.out.println();
    }

}
