// This is a mutant program.
// Author : ysma

package main.mutants;


import main.Problem;

import java.util.Arrays;


public class Problem_mirror_AORB_20 extends Problem
{


    public Problem_mirror_AORB_20(int numberOfElements, int[] input, String problemDescription )
    {
        super(numberOfElements, input, problemDescription);
    }

    public Problem_mirror_AORB_20(Problem problem) {
        super(problem.getNumberOfElements(), problem.getInput(), problem.getProblemDescription());
    }

    public  boolean solve()
    {
        if (numberOfElements < 2 || numberOfElements > 100) {
            throw new RuntimeException( "The number of elements is outside the interval [2, 100]." );
        }
        if (input == null) {
            throw new RuntimeException( "Input array is null." );
        }
        if (input.length != numberOfElements) {
            throw new RuntimeException( "Input array length is different than the N given." );
        }
        for (int i = 0; i < numberOfElements; i++) {
            if (input[i] < 0) {
                throw new RuntimeException( "The element on position " + i + " is not a natural number." );
            }
        }
        int lastElement = input[numberOfElements - 1];
        int lastElementMirror = getMirror( lastElement );
        for (int i = 0; i < numberOfElements - 1; i++) {
            if (input[i] == lastElementMirror) {
                return true;
            }
        }
        return false;
    }

    private  int getMirror( int x )
    {
        int y = 0;
        while (x != 0) {
            y = y * 10 - x % 10;
            x /= 10;
        }
        return y;
    }

}
