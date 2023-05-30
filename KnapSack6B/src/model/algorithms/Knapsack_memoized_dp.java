package model.algorithms;

import java.util.ArrayList;

/**
 * @author Caleb Krauter
 * @version 1.0
 *
 *
 * NOTE: I use main method for each of my model.algorithms to reduce instantiations/method/constructor
 * calls to improve experimental accuracy in results.
 */

/**
 * An implementation of the knapsack memoized algorithm.
 */
public class Knapsack_memoized_dp {
    /**
     * myWeight is an array of weights which correspond to items at each index.
     */
    private static int[] myWeight;
    /**
     * myValue is an array of values which correspond to items at each index.
     */
    private static int[] myValue;
    /**
     * myDPTable is a 2D array that holds the integers representing values
     * for each 'i' item at each 'j' capacity.
     */
    private static int[][] myDPTable;
    /**
     * myCapacity is the weight limit on the knapsack.
     */
    private static int myCapacity;
    /**
     * myNumOfItems is the integer number of items considered for
     * putting in the knapsack.
     */
    private static int myNumOfItems;
    /**
     * myOptimalSetOfIndices is an ArrayList of Object type Integer. Its purpose is
     * to contain any index/indices corresponding to any item(s) in the optimal set.
     */
    private static ArrayList<Integer> myOptimalSetOfIndices;

    /**
     * The constructor initializes the data, DPTable, makes a call to the knapsack_memoized_dp() method,
     * prints results and contains test cases and timing results.
     */

    public Knapsack_memoized_dp(int[] theWeight, int[] theValue, int theCapacity, int theNumOfItems, int[][] theDPTable) {
        myWeight = theWeight;
        myValue = theValue;
        myCapacity = theCapacity;
        myNumOfItems = theNumOfItems;
        myDPTable = theDPTable;


        // Initialize the DPTABLE. This is done here to reduce unnecessary method calls for performance benefits.
        // As the problem specifications (per the Levitin book (pg 295)) state
        // the table must be initalized to a value to represent a "null" state.
        // We use -1 to do this.
        for (int i = 0; i <= myNumOfItems; i++) {
            for (int j = 0; j <= myCapacity; j++ ) {
                myDPTable[i][j] = -1;

            }
        }

        int i = myNumOfItems;
        int j = myCapacity;

        // Call to the knapsack_memoized_dp method
        int maxVal = knapsack_memoized_dp(i, j);

        // Backtracking is done here to get the index of all optimal items added.
        // This is done here to reduce method calls.
        int curWeight = myCapacity;
        myOptimalSetOfIndices = new ArrayList<>();
        for (int curItem = myDPTable.length - 1; curItem > 0; curItem--) {
            if (myDPTable[curItem][curWeight] != myDPTable[curItem-1][curWeight]) {
                myOptimalSetOfIndices.add(curItem-1);
                curWeight -= myWeight[curItem-1];

            }
        }

        // Printing out the data required as output is done here to reduce unnecessary method calls.
        System.out.print("[");
        for (int p = myOptimalSetOfIndices.size() - 1; p >= 0; p--) {
            System.out.print("index: " + myOptimalSetOfIndices.get(p) + " ");
        }
        System.out.print("]");
        System.out.println();
        System.out.println("Knapsack_memoized_dp max value | " + maxVal);
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
    private static int knapsack_memoized_dp(int i, int j) {
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
           if (j < myWeight[i-1]) {
               // Since the previous item's weight is too much, set val and recursively call to the previous item.
               val = knapsack_memoized_dp(i-1, j);
           } else {
               // This is similar to the recurrence relation found on ((page 293) of Levitin)
               // Case when j - w_i >= 0
               if (knapsack_memoized_dp(i-1, j) < myValue[i-1] + knapsack_memoized_dp(i-1, j-myWeight[i-1])) {
                   val = myValue[i-1] + knapsack_memoized_dp(i-1, j-myWeight[i-1]);
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
        for (int i = 0; i <= myNumOfItems; i++) {
            System.out.println();
            for (int j = 0; j <= myCapacity; j++) {
                if (i > 0 && j > 0) {
                    System.out.print(myDPTable[i][j] + ", ");
                } else {
                    System.out.print(myDPTable[i][j] + " , ");
                }
            }
        }
    }
}
