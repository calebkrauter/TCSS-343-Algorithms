package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Caleb Krauter
 */

// Write out one more timme
    // break down on paper

public class KnapSackBruteForce01 {
    static final int WEIGHT_INDEX = 0;
    static final int VALUE_INDEX = 1;
    public static void main(String[] args) {
        int W = 5;
        ArrayList<int[]> items = new ArrayList<>();
        // fill item with item data
        items.add(new int[]{2, 12});
        items.add(new int[]{1, 10});
        items.add(new int[]{3, 20});
        items.add(new int[]{2, 15});

        // Create Powerset
        ArrayList<ArrayList<int[]>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<>());
        ArrayList<int[]> optimalSet = new ArrayList<>();
        for (int[] item : items) {
            ArrayList<ArrayList<int[]>> curItems = new ArrayList<>();
            for (ArrayList<int[]> subset : powerSet) {
                ArrayList<int[]> curItem = new ArrayList<>(subset);
                curItem.add(item);
                curItems.add(curItem);
            }
            powerSet.addAll(curItems);
        }
        //        powerSet.remove(0);

        // create optimal set
        int bestValue = 0;
        int bestWeight = 0;
        for (ArrayList<int[]> item : powerSet) {
            int sumValue = 0;
            int sumWeight = 0;
            for (int[] itemData : item) {
                sumWeight += itemData[WEIGHT_INDEX];
                sumValue += itemData[VALUE_INDEX];
            }
            if (sumWeight <= W && sumValue > bestValue) {
                bestValue = sumValue;
                bestWeight = sumWeight;
                optimalSet = item;
            }
        }
        // PRINT

        for (int j = 0; j < optimalSet.size(); j++)
            for (int i = 0; i < items.size(); i++) {
                if (optimalSet.get(j)[0] == items.get(i)[0] && optimalSet.get(j)[1] == items.get(i)[1]) {
                    System.out.print(i);
                }
            }
    }


}
