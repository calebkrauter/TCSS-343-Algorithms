package model.algorithms;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Caleb Krauter
 * @version 1.0
 * Collaborated with Parker and Anthony on 6B.
 */

/**
 * An implementation of the knapsack memoized algorithm.
 */
public class Knapsack_memoized_dp {
    /**
     * weight is an array of weights which correspond to items at each index.
     */
    private  int[] weight;
    /**
     * value is an array of values which correspond to items at each index.
     */
    private int[] value;
    /**
     * myDPTable is a 2D array that holds the integers representing values
     * for each 'i' item at each 'j' capacity.
     */
    private int[][] myDPTable;
    /**
     * W is the weight limit on the knapsack.
     */
    private int W;
    /**
     * n is the integer number of items considered for
     * putting in the knapsack.
     */
    private int n;
    /**
     * myOptimalSetOfIndices is an ArrayList of Object type Integer. Its purpose is
     * to contain any index/indices corresponding to any item(s) in the optimal set.
     */
    private ArrayList<Integer> myOptimalSetOfIndices;

    /**
     * The constructor initializes the data, DPTable, makes a call to the knapsack_memoized_dp() method,
     * prints results and contains test cases and timing results.
     */

    public Knapsack_memoized_dp(int[] theWeight, int[] theValue, int theCapacity, int theNumOfItems, int[][] theDPTable) {
        weight = theWeight;
        value = theValue;
        W = theCapacity;
        n = theNumOfItems;
        myDPTable = theDPTable;
        long endTime = 0;
        long totalTime = 0;
        long startTime = 0;

        // Initialize the DPTABLE. This is done here to reduce unnecessary method calls for performance benefits.
        // As the problem specifications (per the Levitin book (pg 295)) state
        // the table must be initalized to a value to represent a "null" state.
        // We use -1 to do this.
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++ ) {
                myDPTable[i][j] = -1;

            }
        }

        int i = n;
        int j = W;

        Date start = new Date();
        startTime = start.getTime();
        // Call to the knapsack_memoized_dp method
        int maxVal = knapsack_memoized_dp(i, j);

        // Backtracking is done here to get the index of all optimal items added.
        // This is done here to reduce method calls.
        int curWeight = W;
        myOptimalSetOfIndices = new ArrayList<>();
        for (int curItem = myDPTable.length - 1; curItem > 0; curItem--) {
            if (myDPTable[curItem][curWeight] != myDPTable[curItem-1][curWeight]) {
                myOptimalSetOfIndices.add(curItem-1);
                curWeight -= weight[curItem-1];

            }
        }

        // Printing out the data required as output is done here to reduce unnecessary method calls.
        System.out.print("Indices : [");
        for (int p = myOptimalSetOfIndices.size() - 1; p >= 0; p--) {
            System.out.print(" " + myOptimalSetOfIndices.get(p) + " ");
        }
        System.out.print("]");
        System.out.println();
        System.out.println("Knapsack_memoized_dp max value | " + maxVal);

        Date finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("knapsack-memoized-dp took : " + totalTime + " ms");

        // Uncomment this block to see what the DPTable looks like
//        printTable();
    }

    // "knapsack-memoized-dp" caused an error so changed the name to "knapsack_memoized_dp" to follow the assignment specs as closely as possible.
    /**
     * The primary method, performs a solution to the Knapsack 01 problem using a
     * Dynamic Programming Memoized approach.
     * @param i an index representing the rows for each item on the DPTable.
     * @param j an index representing the cols for each capacity on the DPTable.
     * @return the maximum value possible in the Knapsack problem.
     */
    private int knapsack_memoized_dp(int i, int j) {
       if (i == 0 || j == 0) {
           return 0;
       }
       if (myDPTable[i][j] != -1) {
           return myDPTable[i][j];
       }
        int val = 0;

       // Any time we find a null state in the table
       if (myDPTable[i][j] < 0) {

           // If current capacity is less than the weight of the previous item.
           if (j < weight[i-1]) {
               // Since the previous item's weight is too much, set val and recursively call to the previous item.
               val = knapsack_memoized_dp(i-1, j);
           } else {
               // This is similar to the recurrence relation found on ((page 293) of Levitin)
               // Case when j - w_i >= 0
               if (knapsack_memoized_dp(i-1, j) < value[i-1] + knapsack_memoized_dp(i-1, j-weight[i-1])) {
                   val = value[i-1] + knapsack_memoized_dp(i-1, j-weight[i-1]);
               } else { // Case when j - w_i < 0
                   val = knapsack_memoized_dp(i-1, j);
               }
           }
       }
       myDPTable[i][j] = val;
       return myDPTable[i][j];
    }

    // Uncomment the method call to see what the table looks like.
    /**
     * Prints the DPTable out.
     */
    private void printTable() {
        for (int i = 0; i <= n; i++) {
            System.out.println();
            for (int j = 0; j <= W; j++) {
                if (i > 0 && j > 0) {
                    System.out.print(myDPTable[i][j] + ", ");
                } else {
                    System.out.print(myDPTable[i][j] + " , ");
                }
            }
        }
    }
}
