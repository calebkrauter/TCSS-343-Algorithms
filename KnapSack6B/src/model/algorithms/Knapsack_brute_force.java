package model.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Caleb Krauter
 * @version 1.0
 */

public class Knapsack_brute_force {

    /**
     * A constant used for accessing weight data of an item.
     */
    final private int WEIGHT_DATA = 0;
    /**
     * A constant used for accessing value data of an item.
     */
    final private int VALUE_DATA = 1;
    /**
     * The arraylist of items with their weights and values contained in their respective int arrays.
     */
    private ArrayList<int[]> myItems;
    /**
     * The weight max of the knapsack.
     */
    private int myCapacity;

    /**
     * The constructor initializes class fields and calls the method containing
     * the brute force solution to the 0-1 knapsack problem.
     * @param theItems
     * @param theCapacity
     * @param theNumOfItems
     */
    public Knapsack_brute_force(ArrayList<int[]> theItems, int theCapacity, int theNumOfItems) {
        myItems = theItems;
        myCapacity = theCapacity;
        long endTime = 0;
        long totalTime = 0;
        long startTime = 0;
        Date start = new Date();
        startTime = start.getTime();

        knapsack_brute_force();

        Date finish = new Date();
        endTime = finish.getTime();
        totalTime += (endTime - startTime);
        System.out.println("Knapsack_brute_force took : " + totalTime + " ms");
        System.out.println();

    }

    // "knapsack-brute-force" caused an error so changed the name to "knapsack_brute_force" to follow the assignment specs as closely as possible.
    /**
     * A brute force implementation of a solution to the 0-1 Knapsack problem.
     */
    private void knapsack_brute_force() {

        ArrayList<ArrayList<int[]>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<>());


//         We want to generate a power-set of all possible item combinations.
        for (int[] itemWithData : myItems) {
            ArrayList<ArrayList<int[]>> curItems = new ArrayList<>();
            for (ArrayList<int[]> subsetOfItems : powerSet) {
                ArrayList<int[]> curItem = new ArrayList<>(subsetOfItems);
                curItem.add(itemWithData);
                curItems.add(curItem);
            }
            powerSet.addAll(curItems);

        }

        // We want to keep the optimal set of items up to date with the most
        // optimal set of items that are valid meaning they do not exceed the capacity.
        int bestValue = 0;
        int sumWeight;
        int sumValue;
        ArrayList<int[]> optimalSet = new ArrayList<>();

        for (ArrayList<int[]> subsetOfItems : powerSet) {
            sumWeight = 0;
            sumValue = 0;
            for (int[] itemWithData : subsetOfItems) {
                sumWeight += itemWithData[WEIGHT_DATA];
                sumValue += itemWithData[VALUE_DATA];
            }

            // We want to make sure the current set of items has a
            // collective weight that does not exceed the capacity.
            if (sumWeight <= myCapacity) {
                // We want to update the bestValue we have found if we find a greater value.
                if (sumValue > bestValue) {
                    bestValue = sumValue;
                    optimalSet = subsetOfItems;
                }
            }
        }

        // We want to print out the indices we have found to be used to access the items of the optimal set.
        System.out.print("[");
        for (int j = 0; j < optimalSet.size(); j++) {

            for (int i = 0; i < myItems.size(); i++) {
                if (optimalSet.get(j)[0] == myItems.get(i)[0] && optimalSet.get(j)[1] == myItems.get(i)[1]) {
                    System.out.print("index:  " + i + " ");
                }
            }
        }
        System.out.print("]");
        System.out.println();

        System.out.println("Knapsack_brute_force max value | " + bestValue);
    }

}
