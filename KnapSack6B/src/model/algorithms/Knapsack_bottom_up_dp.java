package model.algorithms;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Caleb Krauter
 * @version 1.0
 * Collaborated with Parker and Anthony on 6B.
 */

/**
 * An implementation of a DP bottom-up solution to the 0-1 Knapsack problem.
 */
public class Knapsack_bottom_up_dp {
    /**
     * weight is an array of weights which correspond to items at each index.
     */
    private int[] weight;
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
     * Constructor initializes the data, myDPTable, makes a call to the dPMemoized() method,
     * prints results and contains test cases and timing results.
     *
     * @param theWeight an array of weights each of which correspond to items.
     * @param theValue an array of values each of which correspond to items.
     * @param theCapacity the max amount of weight allowed in the knapsack.
     * @param theNumOfItems the max number of items to choose from.
     * @param theDPTable a 2D array that holds the integers representing values
     *                   for each 'i' item at each 'j' capacity.
     */
    public Knapsack_bottom_up_dp(int[] theWeight, int[] theValue, int theCapacity, int theNumOfItems, int[][] theDPTable) {
        weight = theWeight;
        value = theValue;
        W = theCapacity;
        n = theNumOfItems;
        myDPTable = theDPTable;

        long endTime = 0;
        long totalTime = 0;
        long startTime = 0;

        Date start = new Date();
        startTime = start.getTime();
        knapsack_bottom_up_dp();

        Date finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("Knapsack_bottom_up_dp max value | " + myDPTable[n][W]);

        System.out.println("Knapsack_bottom_up_dp took : " + totalTime + " ms");
        System.out.println();
    }

    // "knapsack-bottom-up-dp" caused an error so changed the name to "knapsack_bottom_up_dp" to follow the assignment specs as closely as possible.
    /**
     * A bottom-up Dynamic Programming implementation of a solution to the 0-1 Knapsack problem.
     */
    private void knapsack_bottom_up_dp() {
        // do check and add


        int curWeight = 0;
        int curVal = 0;
        ArrayList<Integer> optimalSetOfIndices;

        for (int i = 1; i <= n; i++) {
            curWeight = weight[i-1];
            curVal = value[i-1];
            for (int j = 1; j <= W; j++) {
                myDPTable[i][j] = myDPTable[i-1][j];

                // We want to get the highest value possible for the item within the weight j and store it in the table.
                if (j >= weight[i-1] && myDPTable[i-1][j] < curVal + myDPTable[i-1][j-curWeight]) {
                    myDPTable[i][j] = myDPTable[i-1][j-curWeight] + curVal;
                } else {
                    myDPTable[i][j] = myDPTable[i-1][j];
                }
            }
        }

        // Backtracking is done here to get the index of all optimal items added.
        int j = W;
        optimalSetOfIndices = new ArrayList<>();
        for (int i = myDPTable.length - 1; i > 0; i--) {
            if (myDPTable[i][j] != myDPTable[i-1][j]) {
                optimalSetOfIndices.add(i-1);
                j -= weight[i-1];

            }
        }

        System.out.print("Indices : [");
        for (int i = optimalSetOfIndices.size() - 1; i >= 0; i--) {
            System.out.print(" " + optimalSetOfIndices.get(i) + " ");
        }
        System.out.print("]");
        System.out.println();


    }

}
