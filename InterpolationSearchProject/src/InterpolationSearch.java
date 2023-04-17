public class InterpolationSearch {

    GenerateInputArray generateInputArray;
    private int low = 0;
    private int[] inputArray = new GenerateInputArray().getInputArray();
    private int high = inputArray.length - 1;
    private int k = 65; // Target value

    // TODO - I observed that when the target value does not exist in the set the interpolation search tries many positions,
    //          many of which appear to be the same position, sometimes going back and forth in direction before giving up.
    //          Is the algorithm wokring as intended or is this a bug?
    // TODO - Fix bug where rarely, the array will generate a 0 in the wrong place meaning it will be after a larger value in order
    //          ruining the order.

    public InterpolationSearch() {
        System.out.println();
        interpolationSearch(k, low, high);
    }

    private int interpolationSearch(int k, int low, int high) {

        while (k >= inputArray[low] && k <= inputArray[high] && low <= high) {
            int probPos = (int) (low + Math.floor(((k - inputArray[low]) * (high - low)) / (inputArray[high] - inputArray[low])));

            System.out.println("Tried pos: " + probPos);

            if (k == inputArray[probPos]) {
                System.out.println("Found target k at pos: " + probPos);
                return probPos;
            }

            if (k > inputArray[low]) {
                low = low + 1;
            } else if (k < inputArray[high]){
                high = high - 1;
            }
        }
            System.out.println("Target never found, return: -1");
            return -1; // not found

    }

}
