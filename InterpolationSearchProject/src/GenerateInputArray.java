import java.util.Arrays;
import java.util.Random;

public class GenerateInputArray {

    private int[] inputArray;
    private int size = 10;
    private int element = 0;
    private int min = 1;
    private int max = 100;
    private Random random;

    // TODO understand why the same pos is tried multiple times. Maybe a rounding error in the formula for position?

    public GenerateInputArray() {
        random = new Random();
        inputArray = new int[generateArraySize()];
        fillArray(inputArray);
        // The input is not always fully sorted so I am doing a call on sort to make sure it is sorted
        Arrays.sort(inputArray);
        printArray();
//        new InterpolationSearch();

    }

    // Generate array size between 50 and 5000
    private int generateArraySize() {
        size = random.nextInt(50, 5000);
        return size;
    }

    private void fillArray(int[] inputArray) {
        int prevElement = 0;
        min = 0;
        max = 10;
        for (int i = 0; i < inputArray.length ; i++) {
            element = random.nextInt(0, 10);
// I have noticed a bug where some smaller values filled the array after the previous larger values.
            // This did not stop the algorithm from finding k.
            if (element > prevElement) {
                inputArray[i] = element;
            } else if (prevElement > element) {
//                System.out.println("prev " + prevElement);
//                System.out.println("element " + element);
                min = element;
                element = element*2 + prevElement + 1;
//                System.out.println("NEW element " + element);

                max = element*2 + prevElement + 1;
                inputArray[i] = element;
            }
            // TODO fix bug, why can't origin be min and bound be max? Doesn't work.
            // THIS block might fix the bug! Not certain. It appears to work as expected now.
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
