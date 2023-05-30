import java.util.ArrayList;
public class Knapy {








    static final int WEIGHT_INDEX = 0;
    static final int VALUE_INDEX = 1;

    public static void main(String[] args) {
        int W = 5;
        ArrayList<int[]> items = new ArrayList<int[]>();
// fill item with item data
        items.add(new int[]{2, 12});
        items.add(new int[]{1, 10});
        items.add(new int[]{3, 20});
        items.add(new int[]{2, 15});
        int bestValue = 0;
        int bestWeight = 0;
// --------- CREATE POWERSET
        ArrayList<ArrayList<int[]>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<>());
        ArrayList<int[]> optimalSet = new ArrayList<>();
        for (int[] itemWithData : items) { // for each array in arraylist
            ArrayList<ArrayList<int[]>> curItems = new ArrayList<>();
            for (ArrayList<int[]> subSetOfItems : powerSet) {
                ArrayList<int[]> curItem = new ArrayList<>(subSetOfItems);
                curItem.add(itemWithData);
                curItems.add(curItem);
            }
            powerSet.addAll(curItems);
        }
//        powerSet.remove(0);

// --------- CREATE OPTIMAL SET


        for (ArrayList<int[]> subSetOfItems : powerSet) {
            int sumValue = 0;
            int sumWeight = 0;
            for (int[] itemData : subSetOfItems) {
                sumWeight += itemData[WEIGHT_INDEX];
                sumValue += itemData[VALUE_INDEX];
            }
            if (sumWeight <= W && sumValue > bestValue) {
                bestValue = sumValue;
                bestWeight = sumWeight;
                optimalSet = subSetOfItems;
            }
        }

// ---------- PRINT INDECIES
        for (int j = 0; j < optimalSet.size(); j++)
            for (int i = 0; i < items.size(); i++) {
                if (optimalSet.get(j)[0] == items.get(i)[0] && optimalSet.get(j)[1] == items.get(i)[1]) {
                    System.out.print(i);
            }
        }
        System.out.print(" with a Capacity of " + bestWeight + " and value of " + bestValue);
    }


}



