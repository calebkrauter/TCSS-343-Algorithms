package model;

import java.util.Random;

/**
 * @author Caleb Krauter
 * @version 1.0
 * Collaborated with Parker and Anthony on 6B.
 */

/**
 * Generates random number.
 */
public class RandomNumberGenerator {
    /**
     * Number of items.
     */
    private static int myNumOfItems;

    /**
     * Constructor for class reference.
     */
    public RandomNumberGenerator() {

    }

    /**\
     * A setter that sets the random number of items given origin and bound.
     * @param theOrigin
     * @param theBound
     */
    public void setTheNumOfItems(int theOrigin, int theBound) {
        myNumOfItems = randomNumberGenerator(theOrigin, theBound);
    }

    /**
     * Gets the random number of items.
     * @return myNumOfItems
     */
    public int getTheNumOfItems() {
        return myNumOfItems;
    }

    /**
     * Returns a random integer given an origin and bound.
     * @param theOrigin
     * @param theBound
     * @return a random integer
     */
    public int randomNumberGenerator(int theOrigin, int theBound) {
        Random random = new Random();
        return random.nextInt(theOrigin, theBound);
    }
}
