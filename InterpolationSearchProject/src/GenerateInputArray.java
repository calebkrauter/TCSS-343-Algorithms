import java.util.Random;

public class GenerateInputArray {

    private int[] inputArray;
    private int size = 10;
    private int element = 0;
    private int min = 1;
    private int max = 100;
    private Random random;

    // TODO make sure bug doesn't happen where 0 is printed after a larger number
    // TODO understand why the same pos is tried multiple times. Maybe a rounding error in the formula for position?

    public GenerateInputArray() {
        random = new Random();
        inputArray = new int[generateArraySize()];
        fillArray(inputArray);
        printArray();
//        new InterpolationSearch();

    }

    // Generate array size between 50 and 5000
    private int generateArraySize() {
        size = random.nextInt(50, 5000);
        return size;
    }

    private void fillArray(int[] inputArray) {
       // Generate value between min = prevVal and max, if value is less than value at prevIndex,min = min + prevVal + 1

        int prevElement = 0;
        min = 0;
        max = 10;
        for (int i = 0; i < inputArray.length ; i++) {
            element = random.nextInt(0, 10);

            if (element > prevElement) {
                inputArray[i] = element;
            } else if (prevElement > element) {
                element = element + prevElement + 1;
                min = element;
                max = element*2 + prevElement + 1;
                inputArray[i] = element;
            }
            // TODO fix bug, why can't origin be min and bound be max? Doesn't work.
            // THIS block might fix the bug! Not certain. It appears to work now.
            if (i > 0 && prevElement > inputArray[i] && inputArray[i] == 0) {

                inputArray[i] = element*2 + prevElement + 1;
            }
            prevElement = element;
        }
    }

    private void printArray() {
        for (int element: inputArray) {
            System.out.print(element + ", ");
        }
    }

    public int[] getInputArray() {
        return inputArray;
    }
}
