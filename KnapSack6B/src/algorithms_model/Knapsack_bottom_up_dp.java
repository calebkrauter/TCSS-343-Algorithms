package algorithms_model;

import java.util.ArrayList;

/**
 * @author Caleb Krauter
 * @version 1.0
 */
public class Knapsack_bottom_up_dp {
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
        myWeight = theWeight;
        myValue = theValue;
        myCapacity = theCapacity;
        myNumOfItems = theNumOfItems;
        myDPTable = theDPTable;
        knapsack_bottom_up_dp();
    }

    // "knapsack-bottom-up-dp" caused an error so changed the name to "knapsack_bottom_up_dp" to follow the assignment specs as closely as possible.
    /**
     * A bottom-up Dynamic Programming implementation of a solution to the 0-1 Knapsack problem.
     */
    private static void knapsack_bottom_up_dp() {
        // do check and add


        int curWeight = 0;
        int curVal = 0;
        ArrayList<Integer> optimalSetOfIndices;

        for (int i = 1; i <= myNumOfItems; i++) {
            curWeight = myWeight[i-1];
            curVal = myValue[i-1];
            for (int j = 1; j <= myCapacity; j++) {
                myDPTable[i][j] = myDPTable[i-1][j];

                // We want to get the highest value possible for the item within the weight j and store it in the table.
                if (j >= myWeight[i-1] && myDPTable[i-1][j] < curVal + myDPTable[i-1][j-curWeight]) {
                    myDPTable[i][j] = myDPTable[i-1][j-curWeight] + curVal;
                } else {
                    myDPTable[i][j] = myDPTable[i-1][j];
                }
            }
        }

        // Backtracking is done here to get the index of all optimal items added.
        int j = myCapacity;
        optimalSetOfIndices = new ArrayList<>();
        for (int i = myDPTable.length - 1; i > 0; i--) {
            if (myDPTable[i][j] != myDPTable[i-1][j]) {
                optimalSetOfIndices.add(i-1);
                j -= myWeight[i-1];

            }
        }

        System.out.print("[");
        for (int i = optimalSetOfIndices.size() - 1; i >= 0; i--) {
            System.out.print("index: " + optimalSetOfIndices.get(i) + " ");
        }
        System.out.print("]");
        System.out.println();


        System.out.println("Knapsack_bottom_up_dp max value | " + myDPTable[myNumOfItems][myCapacity]);

    }

}
