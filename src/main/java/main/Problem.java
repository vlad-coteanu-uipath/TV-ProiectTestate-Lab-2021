package main;

/*
 * Problem:
 *
 * Given an array of N (2 <= N <= 100) natural numbers, check if the reverse of the last number appears among the other elements of the array"
 *
 * For example:
 * Input:
 * [10, 321, 5, 23, 12, 17, 123]
 * Output:
 * true
 *
 * Input:
 * [3, 5, 12, 89]
 * Output:
 * false
 *
 * */

import java.util.Arrays;

public class Problem {

    protected int numberOfElements;
    protected int[] input;
    protected String problemDescription;

    public Problem(int numberOfElements, int[] input, String problemDescription) {
        this.input = input;
        this.numberOfElements = numberOfElements;
        this.problemDescription = problemDescription;
    }

    public boolean solve() {

        if(numberOfElements < 2 || numberOfElements > 100) {
            throw new RuntimeException("The number of elements is outside the interval [2, 100].");
        }

        if(input == null) {
            throw new RuntimeException("Input array is null.");
        }

        if(input.length != numberOfElements) {
            throw new RuntimeException("Input array length is different than the N given.");
        }

        for(int i = 0; i < numberOfElements; i ++) {
            if(input[i] < 0) {
                throw new RuntimeException("The element on position " + i + " is not a natural number.");
            }
        }

        int lastElement = input[numberOfElements - 1];
        int lastElementMirror = getMirror(lastElement);
        for(int i = 0; i < numberOfElements - 1; i++) {
            if(input[i] == lastElementMirror) {
                return true;
            }
        }
        return false;
    }

    private int getMirror(int x) {
        int y = 0;

        while(x != 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }

        return y;
    }

    @Override
    public String toString() {
        String arrayDisplay = input != null ? Arrays.toString(input) : "null";
        if(arrayDisplay.length() > 20) {
            arrayDisplay = arrayDisplay.substring(0, 10) + " ... " + arrayDisplay.substring(arrayDisplay.length() - 10);
        }
        return problemDescription + ": " +
                "numberOfElements=" + numberOfElements +
                ", input=" + arrayDisplay;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int[] getInput() {
        return input;
    }

    public String getProblemDescription() {
        return problemDescription;
    }
}
