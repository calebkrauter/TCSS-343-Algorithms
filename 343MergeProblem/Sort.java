import java.util.*;


/**
 * A class that contains a group of sorting algorithms.
 * The input to the sorting algorithms is assumed to be
 * an array of integers.
 * 
 * @author Donald Chinn
 * @version January 30, 2018
 */
public class Sort {

    // Constructor for objects of class Sort
    public Sort() {
    }

    /**
     * Given an array of integers, sort the array
     * (ascending order) using insertion sort.
     * @param data  an array of integers
     */
    public static void insertionSort (int[] data) {
        for (int i = 1; i < data.length; i++) {
            int temp = data[i];
            int j = i-1;
            while ((j >= 0) && (data[j] > temp)) {
                data[j+1] = data[j];
                j--;
            }
            data[j+1] = temp;
        }
    }
    
   /**
     * Given an array of integers, sort the array
     * (ascending order) using mergesort.
     * @param data  an array of integers
     */
   public static void mergesort (int[] data) {
        mergesortRecursive (data, 0, data.length - 1);
    }

   /**
    * Given an array of integers, sort the array
    * (ascending order) between indexes low and high (inclusive)
    * using mergesort.
    * @param data  an array of integers
    * @param low   low index
    * @param high  high index
    */
   private static void mergesortRecursive (int[] data, int low, int high) {
        if (low < high) {
            mergesortRecursive (data,
                                low,
                                low + (high-low)/2 );
            mergesortRecursive (data,
                                low + (high-low)/2 + 1,
                                high);
            merge (data, low, high);
        }
    }
    
    
    /**
     * Given an array of integers and indexes low and high,
     * where items low through low + (high-low)/2 are sorted (ascending)
     * and items low + (high-low)/2 + 1 through high are sorted,
     * merge the two sorted subarrays into one sorted subarray 
     * (ascending order) between indexes low and high (inclusive).
     * @param data  an array of integers
     * @param low   low index
     * @param high  high index
     */
    private static void merge (int[] data, int low, int high) {
        // create a new array for the sorted data
        int[] temp = new int[high - low + 1];
        
        int lowIndex = low;
        int highIndex = low + (high-low)/2 + 1;
        int midIndex = low + (high-low)/2;
        int tempIndex = 0;
        
        while ((lowIndex <= midIndex) &&
            (highIndex <= high)) {
            if (data[lowIndex] < data[highIndex]) {
                temp[tempIndex] = data[lowIndex];
                tempIndex++;
                lowIndex++;
            } else {
                temp[tempIndex] = data[highIndex];
                tempIndex++;
                highIndex++;
            }
        }
        
        if (lowIndex > midIndex) {
            // low subarray finished first
            while (highIndex <= high) {
                temp[tempIndex] = data[highIndex];
                tempIndex++;
                highIndex++;
            }
        
        } else {
            // high subarray finished first
            while (lowIndex <= midIndex) {
                temp[tempIndex] = data[lowIndex];
                tempIndex++;
                lowIndex++;
            }
        }
        
        // copy data back from temp to data
        int dataIndex;
        for (tempIndex = 0, dataIndex = low;
            dataIndex <= high;
            tempIndex++, dataIndex++) {
            
            data[dataIndex] = temp[tempIndex];
        }
        
    }
    
    
    /**
     * Given an array of integers and an integer k, sort the array
     * (ascending order) using k-way mergesort.
     * @param data  an array of integers
     * @param k     the k in k-way mergesort
     */
    public static void kwayMergesort (int[] data, int k) {
        kwayMergesortRecursive (data, 0, data.length - 1, k);
    }
    
    /**
     * The recursive part of k-way mergesort.
     * Given an array of integers (data), a low index, high index, and an integer k,
     * sort the subarray data[low..high] (ascending order) using k-way mergesort.
     * @param data  an array of integers
     * @param low   low index
     * @param high  high index
     * @param k     the k in k-way mergesort
     */
    private static void kwayMergesortRecursive (int[] data, int low, int high, int k) {
        if (low < high) {
            for (int i = 0; i < k; i++) {
                kwayMergesortRecursive (data,
                                        low + i*(high-low+1)/k,
                                        low + (i+1)*(high-low+1)/k - 1,
                                        k);
            }
            merge (data, low, high, k);
        }
    }
    

    /**
     * Given an array of integers (data), a low index, a high index, and an integer k,
     * sort the subarray data[low..high].  This method assumes that each of the
     * k subarrays  data[low + i*(high-low+1)/k .. low + (i+1)*(high-low+1)/k - 1],
     * for i = 0..k-1, are sorted.
     * @param data  an array of integers
     * @param low   low index
     * @param high  high index
     * @param k     the k in k-way mergesort
     */
    public static void merge (int[] data, int low, int high, int k) {
    
        if (high < low + k) {
            // the subarray has k or fewer elements
            // just make one big heap and do deleteMins on it
            Comparable[] subarray = new MergesortHeapNode[high - low + 1];
            for (int i = 0, j = low; i < subarray.length; i++, j++) {
                subarray[i] = new MergesortHeapNode(data[j], 0);
            }
            BinaryHeap heap = BinaryHeap.buildHeap(subarray);

            for (int j = low; j <= high; j++) {
                try {
                    data[j] = ((MergesortHeapNode) heap.deleteMin()).getKey();
                }
                catch (EmptyHeapException e) {
                    System.out.println ("Tried to delete from an empty heap.");
                }
            }
            
        } else {
            // YOUR CODE TO DO A k-WAY MERGE GOES HERE

        /*
        1. Define an array of integers of size k. These are the "fingers" to indicate where we are in each subarray.
         We initialize according to the formula in the comment. This will be a for loop that iterates k times.
        2. We fill it with the data at each of initial locations of each subarray. Then we call buildHeap on this array.
        2. We define an array of integers of size high - low + 1. This is to keep track of the sorted array that will be copied back into data[low .. high].
        3. In the main loop, we iterate exactly high - low + 1 times:
        + deleteMin from the heap. Put the integer from that MergesortHeapNode into temp.
        + increment the index (the "finger") for the corresponding subarray. If it is beyond the end of the subarray, do nothing. If it isn't at the end,
         then create a MergesortHeapNode based on the item in the subarray, and then insert it into the heap.
        4. After the loop, copy the elements of temp into the appropriate place in data.
         */


            // 1. Define an array of integers of size k. These are the "fingers" to indicate where we are in each subarray.
            // We initialize according to the formula in the comment. This will be a for loop that iterates k times.
            int[] fingers = new int[k];
            MergesortHeapNode[] mSHN =  new MergesortHeapNode[k];

            for (int i = 0, j = low; i < k; i++, j++) {
                fingers[i] = data[low + i*(high-low+1)/k];

                // We fill it with the data at each of initial locations of each subarray.
                mSHN[i] = new MergesortHeapNode(fingers[i], j);
            }
            // Then we call buildHeap on this array.
            BinaryHeap heap = BinaryHeap.buildHeap(mSHN);
            //  We define an array of integers of size high - low + 1.
            //  This is to keep track of the sorted array that will be copied back into data[low .. high].
            int[] temp = new int[high - low + 1];

            //  In the main loop, we iterate exactly high - low + 1 times:
            int i = 0;
            int j = low;
            while (!heap.isEmpty() && i < high - low + 1) {

                try {
                    //   + deleteMin from the heap. Put the integer from that MergesortHeapNode into temp.
                    temp[i] = ((MergesortHeapNode) heap.deleteMin()).getKey();
                } catch (EmptyHeapException e) {
                    throw new RuntimeException(e);
                }

                if (low + (i+1)*(high-low+1)/k - 1 < k) {
                    if (i < temp.length) { // Increment i without going out of bounds
                            i++; // + increment the index (the "finger") for the corresponding subarray.
                        }
                    // If it isn't at the end, then create a MergesortHeapNode based on the item in the subarray,
                    MergesortHeapNode newMSHN = new MergesortHeapNode(low + i*(high-low+1)/k, j);
                    heap.insert(newMSHN); // and then insert it into the heap.
                }// If it is beyond the end of the subarray, do nothing.
                i++;
                j++;
            }
            //  After the loop, copy the elements of temp into the appropriate place in data.
            for (int m = 0, p = low; m < temp.length; m++, p++) {
                data[p] = temp[m];
            }

        }
    }
    
    
    /**
     * Given an integer size, produce an array of size random integers.
     * The integers of the array are between 0 and size (inclusive) with
     * random uniform distribution.
     * @param size  the number of elements in the returned array
     * @return      an array of integers
     */
    public static int[] getRandomArrayOfIntegers(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (int) ((size + 1) * Math.random());
        }
        return data;
    }
    

    /**
     * Given an integer size, produce an array of size random integers.
     * The integers of the output array are between 0 and size-1 with
     * exactly one of each in the array.  Each permutation is generated
     * with random uniform distribution.
     * @param size  the number of elements in the returned array
     * @return      an array of integers
     */
    public static int[] getRandomPermutationOfIntegers(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
        // shuffle the array
        for (int i = 0; i < size; i++) {
            int temp;
            int swap = i + (int) ((size - i) * Math.random());
            temp = data[i];
            data[i] = data[swap];
            data[swap] = temp;
        }
        return data;
    }


    /**
     * Perform checks to see if the algorithm has a bug.
     */
    private static void testCorrectness() {
        int[] data = getRandomPermutationOfIntegers(100);
        
        for (int i = 0; i < data.length; i++) {
            System.out.println("data[" + i + "] = " + data[i]);
        }
         int k = 100;
        kwayMergesort(data, k);
        
        // verify that data[i] = i
        for (int i = 0; i < data.length; i++) {
            if (data[i] != i) {
                System.out.println ("Error!  data[" + i + "] = " + data[i] + ".");
            }
            // For testing
//            System.out.println("data[" + i + "] = " + data[i]);
        }
    }
    
    
    /**
     * Perform timing experiments.
     */
    private static void testTiming () {
        // timer variables
        long totalTime = 0;
        long startTime = 0;
        long finishTime = 0;

        // start the timer
        Date startDate = new Date();
        startTime = startDate.getTime();

//        int n = 200000;
//        int n = 400000;
//        int n = 800000;
//        int n = 1600000;
        int n = 3200000;

//        int n = 1600000;    // n = size of the array
//        int k = 2;         // k = k in k-way mergesort

        int k = 2;
//        int k = 3;
//        int k = 5;
//        int k = 10;
//        int k = 20;
//        int k = 50;
        int[] data = getRandomArrayOfIntegers(n);
//        heapSort(data);
//        insertionSort(data);
        mergesort(data);
//        kwayMergesort(data, k);
    
        // stop the timer
        Date finishDate = new Date();
        finishTime = finishDate.getTime();
        totalTime += (finishTime - startTime);
        
        System.out.println("** Results for k-way mergesort:");
//        System.out.println("** Results for mergesort:");
//        System.out.println("** Results for insertionSort:");
//        System.out.println("** Results for heapSort:");
        System.out.println("    " + "n = " + n + "    " + "k = " + k);
        System.out.println("    " + "Time: " + totalTime + " ms.");
    }
    
    
    /**
     * code to test the sorting algorithms
     */
    public static void main (String[] argv) {
        testCorrectness();
        testTiming();
    }
}
