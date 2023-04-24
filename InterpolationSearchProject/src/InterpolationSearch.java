public class InterpolationSearch {

    GenerateInputArray generateInputArray;
    private int low = 0;
    private int[] inputArray = new GenerateInputArray().getInputArray();
    private int high = inputArray.length - 1;
    private int k = 65; // Target value

    public InterpolationSearch() {
        System.out.println();
        interpolationSearch(k, low, high);
    }

    private int interpolationSearch(int k, int low, int high) {
        while (k >= inputArray[low] && k <= inputArray[high] && low <= high) {
            int probPos = (int) (low + Math.floor(((k - inputArray[low]) * (high - low)) / (inputArray[high] - inputArray[low])));
            System.out.println("Tried pos: " + probPos);
            if (probPos <= high && probPos >= low) {
                if (inputArray[probPos] == k) {
                    System.out.println("Found target k at pos: " + probPos);
                    return probPos;
                } else if (inputArray[probPos] < k) {
                    low = probPos + 1;
                } else if (inputArray[probPos] > k) {
                    high = probPos - 1;
                }
            } else {
                break; // Bad prob position found
            }
        }
        System.out.println("Not found return -1");
        return -1;

    }

}
