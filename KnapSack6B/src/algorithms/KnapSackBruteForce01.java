package algorithms;

import java.util.ArrayList;

/**
 * @author Caleb Krauter
 */

// Write out one more timme
    // break down on paper

public class KnapSackBruteForce01 {

    static final private int WEIGHT_DATA = 0;
    static final private int VALUE_DATA = 1;

    public KnapSackBruteForce01() {

    }

    public static void main(String[] args) {

        // Init items
        int Capacity = 5;
        ArrayList<int[]> items = new ArrayList<>();
        items.add(new int[]{2, 12});
        items.add(new int[]{1, 10});
        items.add(new int[]{3, 20});
        items.add(new int[]{2, 15});

        // init pset
        ArrayList<ArrayList<int[]>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<>());
        ArrayList<int[]> optimalSet = new ArrayList<>();
        for (int[] itemWithData : items) {
            ArrayList<ArrayList<int[]>> curItems = new ArrayList<>();
            for (ArrayList<int[]> subsetOfItems : powerSet) {
                ArrayList<int[]> curItem = new ArrayList<>(subsetOfItems);
                curItem.add(itemWithData);
                curItems.add(curItem);
            }
            powerSet.addAll(curItems);

        }

        // make optimal set
        int bestValue = 0;
        int sumWeight;
        int sumValue;
        for (ArrayList<int[]> subsetOfItems : powerSet) {
            sumWeight = 0;
            sumValue = 0;
            for (int[] itemWithData : subsetOfItems) {
                sumWeight += itemWithData[WEIGHT_DATA];
                sumValue += itemWithData[VALUE_DATA];
            }
            if (sumWeight <= Capacity) {
                if (sumValue > bestValue) {
                    bestValue = sumValue;
                    optimalSet = subsetOfItems;
                }
            }
        }

        // print indexes

        System.out.print("[");
        for (int j = 0; j < optimalSet.size(); j++)
            for (int i = 0; i < items.size(); i++) {
                if (optimalSet.get(j)[0] == items.get(i)[0] && optimalSet.get(j)[1] == items.get(i)[1]) {
                     System.out.print("index: " + i + " ");
                }
            }
        System.out.print("]");
        System.out.println();

        System.out.println("Max val " + bestValue);





















    }


    }



