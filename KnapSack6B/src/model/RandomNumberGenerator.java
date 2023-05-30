package model;

import java.util.Random;

public class RandomNumberGenerator {
    private static int myNumOfItems;
    public RandomNumberGenerator() {

    }

    public void setTheNumOfItems(int theOrigin, int theBound) {
        myNumOfItems = randomNumberGenerator(theOrigin, theBound);
    }

    public int getTheNumOfItems() {
        return myNumOfItems;
    }
    public int randomNumberGenerator(int theOrigin, int theBound) {
        Random random = new Random();
        return random.nextInt(theOrigin, theBound);
    }
}
