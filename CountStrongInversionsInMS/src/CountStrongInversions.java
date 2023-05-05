import java.util.*;

public class CountStrongInversions {

    public static void main(String[] args) {
        int[] arr = generateRandomArray(10);
        System.out.println(Arrays.toString(arr));
        int count = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("Count: " + count);
    }

    public static int mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        return _mergeSort(arr, temp, 0, arr.length - 1);
    }

    public static int _mergeSort(int[] arr, int[] temp, int left, int right) {
        int mid, count = 0;
        if (right > left) {
            mid = (right + left) / 2;
            count += _mergeSort(arr, temp, left, mid);
            count += _mergeSort(arr, temp, mid + 1, right);
            count += merge(arr, temp, left, mid + 1, right);
        }
        return count;
    }

    public static int merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid, k = left;
        int count = 0;
        while (i <= mid - 1 && j <= right) {
            if (arr[j] < arr[i]) {
                temp[k++] = arr[j++];
                count += mid - i;
            } else {
                temp[k++] = arr[i++];
            }
        }
        while (i <= mid - 1) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
        return count;
    }

    public static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(n) + 1;
        }
        return arr;
    }
}

//
//// Java implementation of the above approach
//class GFG
//{
//
//    // Function that sorts the input array
//    // and returns the number of inversions
//    // in the array
//    // Recursive function that sorts the input
//    // array and returns the number of
//    // inversions in the array
//    // Function that merges the two sorted arrays
//    // and returns the inversion count in the arrays
//    // Driver code
//    public static void main (String[] args)
//    {
//        int arr[] = { 1, 20, 6, 4, 5 };
//        int n = arr.length;
//
//        System.out.println(mergeSort(arr, n));
//    }
//
//    static int mergeSort(int arr[], int array_size)
//    {
//        int temp[] = new int[array_size];
//        return _mergeSort(arr, temp, 0, array_size - 1);
//    }
//
//    static int _mergeSort(int arr[], int temp[],
//                          int left, int right)
//    {
//        int mid, inv_count = 0;
//        if (right > left)
//        {
//
//            // Divide the array into two parts and
//            // call _mergeSortAndCountInv()
//            // for each of the parts
//            mid = (right + left) / 2;
//
//            // Inversion count will be sum of the
//            // inversions in the left-part, the right-part
//            // and the number of inversions in merging
//            inv_count = _mergeSort(arr, temp, left, mid);
//            inv_count += _mergeSort(arr, temp, mid + 1, right);
//
//            // Merge the two parts
//            inv_count += merge(arr, temp, left,
//                    mid + 1, right);
//        }
//        return inv_count;
//    }
//
//    static int merge(int arr[], int temp[], int left,
//                     int mid, int right)
//    {
//        int i, j, k;
//        int inv_count = 0;
//
//        // i is the index for the left subarray
//        i = left;
//
//        // j is the index for the right subarray
//        j = mid;
//
//        // k is the index for the resultant
//        // merged subarray
//        k = left;
//
//        // First pass to count number
//        // of significant inversions
//        while ((i <= mid - 1) && (j < arr.length))
//        {
//            if (arr[i] > 2 * arr[j])
//            {
//                inv_count += (mid - i);
//                j++;
//            }
//            else
//            {
//                i++;
//            }
//        }
//
//        // i is the index for the left subarray
//        i = left;
//
//        // j is the index for the right subarray
//        j = mid;
//
//        // k is the index for the resultant
//        // merged subarray
//        k = left;
//
//        // Second pass to merge the two sorted arrays
//        while ((i <= mid - 1) && (j <= right))
//        {
//            if (arr[i] <= arr[j])
//            {
//                temp[k++] = arr[i++];
//            }
//            else
//            {
//                temp[k++] = arr[j++];
//            }
//        }
//
//        // Copy the remaining elements of the left
//        // subarray (if there are any) to temp
//        while (i <= mid - 1)
//            temp[k++] = arr[i++];
//
//        // Copy the remaining elements of the right
//        // subarray (if there are any) to temp
//        while (j <= right)
//            temp[k++] = arr[j++];
//
//        // Copy back the merged elements to
//        // the original array
//        for (i = left; i <= right; i++)
//            arr[i] = temp[i];
//
//        return inv_count;
//    }
//}
