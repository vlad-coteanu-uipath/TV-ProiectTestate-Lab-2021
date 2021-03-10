package main.utilities;

import javafx.util.Pair;
import main.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Class purpose is to provide test data for the main test class: ProblemTest
 *
 * Should be able to provide test data based on the following techniques:
 * a) equivalence partitioning
 * b) boundary value analysis
 * c) cause-effect graphing
 */
public class TestDataProvider {

    public TestDataProvider() {}

    /**
     * Input values:
     * n - between 2 and 100 -> therefore, there are 3 different partitions:
     *     n < 2
     *     2 <= n <= 100
     *     100 < n
     *
     * inputData - array that should have the same length with n and have positive values
     *             therefore I distinguished 5 partitions:
     *     inputData is null
     *     inputData is not null and does not have the length equal to n
     *     inputData is not null, has the length n, but does not have all the values positive
     *     inputData is not null, has the length n and has only positive values, but the reverse of the last number is
     *               not equal to any other element in the array, such that the result is False
     *     inputData is not null, has the length n, has only positive numbers, and the reverse of the last number is
     *               equal to at least one of the other elements in the array. such that the result is True
     *
     * Considering the above possibilities, there are 5 possible cases ( n in [2, 100] x inputData possibilities ) plus
     * 2 cases when n is outside the boundaries => 7 equivalence partitions.
     *
     */
    public List<Pair<Problem, Result>> getEquivalencePartitioningTestData() {

        List<Pair<Problem, Result>> testData = new ArrayList<Pair<Problem, Result>>();

        // 1. n < 2
        Problem problem1 = new Problem(0, new int[] {1, 2, 3, 4}, "N < 2");
        Result result1 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem1, result1));

        // 2. n > 100
        Problem problem2 = new Problem(123, new int[] {1, 2, 3, 4}, "N > 100");
        Result result2 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem2, result2));

        // 3. n inside [2, 100], inputData is null
        Problem problem3 = new Problem(10, null, "N inside [2, 100] and null input data");
        Result result3 = Result.makeErrorResult("Input array is null.");
        testData.add(new Pair<Problem, Result>(problem3, result3));

        // 4. n inside [2, 100], inputData length is different than n
        Problem problem4 = new Problem(3, new int[] {1, 2, 3, 4, 5, 6}, "N inside [2, 100] and input data of invalid length");
        Result result4 = Result.makeErrorResult("Input array length is different than the N given.");
        testData.add(new Pair<Problem, Result>(problem4, result4));

        // 5. n inside [2, 100], inputData has length n but has negative values
        Problem problem5 = new Problem(6, new int[] {1, 2, -3, 4, -5, 6}, "N inside [2, 100] and input data with negative values");
        Result result5 = Result.makeErrorResult("The element on position 2 is not a natural number.");
        testData.add(new Pair<Problem, Result>(problem5, result5));

        // 6. n inside [2, 100], inputData has length n, positive values, but the reverse of the last number is not
        //    equal to any other element inside the array
        Problem problem6 = new Problem(6, new int[] {10, 20, 30, 40, 50, 60}, "N inside [2, 100] and the result is false");
        Result result6 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem6, result6));

        // 7. n inside [2, 100], inputData has length n, positive values, and the reverse of the last number is equal
        //    to at least one other number inside the array
        Problem problem7 = new Problem(6, new int[] {10, 11, 12, 13, 14, 31}, "N inside [2, 100] and the result is true");
        Result result7 = Result.makeNormalResult(true);
        testData.add(new Pair<Problem, Result>(problem7, result7));

        return testData;
    }


    /**
     * For generating test data based on boundary analysis, we should consider the following possibilities:
     * n, which should belong to the interval [2, 100]:
     *      - values 0 and 1, for the case when n < 2
     *      - values 2, 50 and 100, for the case when n belong to the interval
     *      - values 101, 150, for the case when n is outside the interval
     * inputData - only valid, this time, with the reverse of the last number existing on:
     *      - first position in the array
     *      - before-the-last position in the array
     *
     * Therefore, there are 3 x 2 cases for a valid n, and 4 more cases when n is outside the boundaries
     * However, when n = 2, first position of the array is equal to before-the-last position, so, 9 testData items
     */
    public List<Pair<Problem, Result>> getBoundaryAnalysisTestData() {

        List<Pair<Problem, Result>> testData = new ArrayList<Pair<Problem, Result>>();

        // 1. n is 0
        Problem problem1 = new Problem(0, new int[] {}, "N is less than the lower bound");
        Result result1 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem1, result1));

        // 2. n is 1
        Problem problem2 = new Problem(1, new int[] {1}, "N is less than the lower bound");
        Result result2 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem2, result2));

        // 3. n is 2, solution is on the first position in the array -> which is the same with the solution being
        // on the before-the-last position in the array
        Problem problem3 = new Problem(2, new int[] {123, 321}, "N is equal to the lower bound, solution is on the position 0");
        Result result3 = Result.makeNormalResult(true);
        testData.add(new Pair<Problem, Result>(problem3, result3));

        // 4. n is 50, the reverse of the last number problem is on position 0
        List<Integer> dummyData1 = new ArrayList<>();
        for(int i = 0; i < 50; i ++) {
            dummyData1.add(i + 1);
        }
        dummyData1.set(0, 321);
        dummyData1.set(49, 123);
        Problem problem4 = new Problem(50, dummyData1.stream().mapToInt(i -> i).toArray(),
                "N is inside the expected bounds, solution is on the position 0");
        Result result4 = Result.makeNormalResult(true);
        testData.add(new Pair<Problem, Result>(problem4, result4));

        // 5. n is 50, the reverse of the last number problem is on position 48
        List<Integer> dummyData2 = new ArrayList<>();
        for(int i = 0; i < 50; i ++) {
            dummyData2.add(i + 1);
        }
        dummyData2.set(48, 321);
        dummyData2.set(49, 123);
        Problem problem5 = new Problem(50, dummyData2.stream().mapToInt(i -> i).toArray(),
                "N is inside the expected bounds, solution is on the before-the-last position ");
        Result result5 = Result.makeNormalResult(true);
        testData.add(new Pair<Problem, Result>(problem5, result5));

        // 6. n is 100, the reverse of the last number problem is on position 0
        List<Integer> dummyData3 = new ArrayList<>();
        for(int i = 0; i < 100; i ++) {
            dummyData3.add(i + 1);
        }
        dummyData3.set(0, 321);
        dummyData3.set(99, 123);
        Problem problem6 = new Problem(100, dummyData3.stream().mapToInt(i -> i).toArray(),
                "N is equal to the higher bound, solution is on the position 0");
        Result result6 = Result.makeNormalResult(true);
        testData.add(new Pair<Problem, Result>(problem6, result6));

        // 7. n is 100, the reverse of the last number problem is on position 98
        List<Integer> dummyData4 = new ArrayList<>();
        for(int i = 0; i < 100; i ++) {
            dummyData4.add(i + 1);
        }
        dummyData4.set(98, 321);
        dummyData4.set(99, 123);
        Problem problem7 = new Problem(100, dummyData4.stream().mapToInt(i -> i).toArray(),
                "N is equal to the higher bound, solution is on the position 99");
        Result result7 = Result.makeNormalResult(true);
        testData.add(new Pair<Problem, Result>(problem7, result7));

        // 8. n is 101
        Problem problem8 = new Problem(101, new int[] {123, 321}, "N is greater than the higher bound.");
        Result result8 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem8, result8));

        // 9. n is 150
        Problem problem9 = new Problem(150, new int[] {123, 321}, "N is greater than the higher bound.");
        Result result9 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem9, result9));

        return testData;

    }

    /**
     * The causes that need to be considered are:
     * C1: N is inside [2, 100]
     * C2: inputArray is null
     * C3: inputArray has the length equal to N
     * C4: inputArray has negative values
     * C5: There is an element equal to the reverse of the last number in the array
     *
     * Ef1: RuntimeException: The number of elements is outside the interval [2, 100].
     * Ef2: RuntimeException: Input array is null.
     * Ef3: RuntimeException: Input array length is different than the N given.
     * Ef4: RuntimeException: The element on position _ is not a natural number.
     * Ef5: true
     * Ef6: false
     *
     * According to src/main/resources/TableFromCauseEffectGraph.jpg, there are minimum 6 cases that need to be handled
     *
     */
    public List<Pair<Problem, Result>> getCauseEffectGraphingTestData() {

        List<Pair<Problem, Result>> testData = new ArrayList<Pair<Problem, Result>>();

        // 1. C1 false -> producing Ef1
        Problem problem1 = new Problem(0, new int[] {1, 2, 3, 4}, "C1 false -> Ef1 true");
        Result result1 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem1, result1));

        // 2. C1 true, C2 true -> producing Ef2
        Problem problem2 = new Problem(10, null, "C1 true, C2 true -> Ef2 true");
        Result result2 = Result.makeErrorResult("Input array is null.");
        testData.add(new Pair<Problem, Result>(problem2, result2));

        // 3. C1 true, C2 false, C3 false -> producing Ef3
        Problem problem3 = new Problem(3, new int[] {1, 2, 3, 4, 5, 6}, "C1 true, C2 false, C3 false -> Ef3 true");
        Result result3 = Result.makeErrorResult("Input array length is different than the N given.");
        testData.add(new Pair<Problem, Result>(problem3, result3));

        // 4. C1 true, C2 false, C3 true, C4 true -> producing Ef4
        Problem problem4 = new Problem(6, new int[] {1, 2, -3, 4, -5, 6}, "C1 true, C2 false, C3 true, C4 true -> Ef4 true");
        Result result4 = Result.makeErrorResult("The element on position 2 is not a natural number.");
        testData.add(new Pair<Problem, Result>(problem4, result4));

        // 5. C1 true, C2 false, C3 true, C4 false, C5 true -> producing Ef5
        Problem problem5 = new Problem(6, new int[] {16, 2, 3, 4, 5, 61}, "C1 true, C2 false, C3 true, C4 false, C5 true -> Ef5 true");
        Result result5 = Result.makeNormalResult(true);
        testData.add(new Pair<Problem, Result>(problem5, result5));

        // 6. C1 true, C2 false, C3 true, C4 false, C5 false -> producing Ef6
        Problem problem6 = new Problem(6, new int[] {10, 11, 12, 13, 14, 38}, "C1 true, C2 false, C3 true, C4 false, C5 false -> Ef6 true");
        Result result6 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem6, result6));

        return testData;

    }


    /**
     * Program graph is inside main/resources/StructuralGraphDiagram
     *
     * More details on each test
     *
     */
    public List<Pair<Problem, Result>> getModifiedConditionDecisionCoverageTestData() {

        List<Pair<Problem, Result>> testData = new ArrayList<Pair<Problem, Result>>();

        /**
         * 1. Line 37: numberOfElements < 2 || numberOfElements > 100 as C1 || C2
         *      - we should consider C1 false C2 true, C1 true C2 false, C1 false C2 false
         *      - possible test data: (120, {1, 2, 3}), (1, {1, 2, 3}), (4, {1, 2, 3, 4})
         */
        Problem problem1 = new Problem(120, new int[] {1, 2, 3}, "Line 37: 'numberOfElements < 2' is false, 'numberOfElements > 100' is true");
        Result result1 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem1, result1));

        Problem problem2 = new Problem(1, new int[] {1, 2, 3}, "Line 37: 'numberOfElements < 2' is true, 'numberOfElements > 100' is false");
        Result result2 = Result.makeErrorResult("The number of elements is outside the interval [2, 100].");
        testData.add(new Pair<Problem, Result>(problem2, result2));

        Problem problem3 = new Problem(4, new int[] {1, 2, 3, 4}, "Line 37: 'numberOfElements < 2' is false, 'numberOfElements > 100' is false");
        Result result3 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem3, result3));

        /**
         * 2. Line 41: input == null as C1
         *      - we should consider C1 true and false
         *      - possible test data: (4, null), (4, {1, 2, 3, 4})
         *      - note: even if second test already exists, I am adding it again, with a different description
         */
        Problem problem4 = new Problem(4, null, "Line 41: 'input == null' is true");
        Result result4 = Result.makeErrorResult("Input array is null.");
        testData.add(new Pair<Problem, Result>(problem4, result4));

        Problem problem5 = new Problem(4, new int[] {1, 2, 3, 4}, "Line 41: 'input == null' is false");
        Result result5 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem5, result5));

        /**
         * 3. Line 45: input.length != numberOfElements as C1
         *      - we should consider C1 true and false
         *      - possible test data: (4, {1, 2, 3, 4}), (4, {1, 2, 3})
         */
        Problem problem6 = new Problem(4, new int[] {1, 2, 3}, "Line 45: 'input.length != numberOfElements' is true");
        Result result6 = Result.makeErrorResult("Input array length is different than the N given.");
        testData.add(new Pair<Problem, Result>(problem6, result6));

        Problem problem7 = new Problem(4, new int[] {1, 2, 3, 4}, "Line 45: 'input.length != numberOfElements' is false");
        Result result7 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem7, result7));

        /**
         * 4. Line 49: i < numberOfElements
         *      - considering the problem statement that until this point, we should have at least 2 elements in the array,
         *      this condition will always be true at least one time, for every test that has true/false as result (problem7)
         *      - also, for every type of input that comes to a true/false result, the condition will become false in the end
         *      - possible test data (4, {1, 2, 3, 4})
         */

        Problem problem8 = new Problem(4, new int[] {1, 2, 3, 4}, "Line 49: 'i < numberOfElements' is true and false");
        Result result8 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem8, result8));

        /**
         * 5. Line 50: input[i] < 0
         *      - we should consider C1 true and false
         *      - possible test data: (4, {-1, 2, 3, 4}) (4, {1, 2, 3, 4}),
         */

        Problem problem9 = new Problem(4, new int[] {-1, 2, 3, 4}, "Line 50: 'input[i] < 0' is true");
        Result result9 = Result.makeErrorResult("The element on position 0 is not a natural number.");
        testData.add(new Pair<Problem, Result>(problem9, result9));

        Problem problem10 = new Problem(4, new int[] {1, 2, 3, 4}, "Line 50: 'input[i] < 0' is false");
        Result result10 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem10, result10));

        /**
         * 6. Line 68: x != 0
         *      - considering the purpose of this condition and the lines that follow it, if we have any non-zero element
         *      on the last position, the condition will be true and false in the same problem
         *      - possible test data (4, {1, 2, 3, 4})
         */

        Problem problem11 = new Problem(4, new int[] {1, 2, 3, 4}, "Line 68: 'x != 0' is true and false");
        Result result11 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem11, result11));

        /**
         * 7. Line 57: i < numberOfElements - 1
         *      - considering that at this point we are assured that the for should iterate trough at least one number,
         *      the condition will be true at least once in a problem that returns true/false.
         *      - also, considering that we return early if the problem result is true, any problem that will have a false
         *      result should have this condition false, at the end of the loop.
         *      - possible test data (4, {1, 2, 3, 4})
         */

        Problem problem12 = new Problem(4, new int[] {1, 2, 3, 4}, "Line 57: 'i < numberOfElements - 1' is true and false");
        Result result12 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem12, result12));

        /**
         * 6. Line 58: input[i] == lastElementMirror
         *      - we should consider C1 true and false
         *      - possible test data (4, {12, 2, 3, 21}) (4, {1, 2, 3, 4})
         */

        Problem problem13 = new Problem(4, new int[] {12, 2, 3, 21}, "Line 58: 'input[i] == lastElementMirror' is true");
        Result result13= Result.makeNormalResult(true);
        testData.add(new Pair<Problem, Result>(problem13, result13));

        Problem problem14 = new Problem(4, new int[] {1, 2, 3, 4}, "Line 58: 'input[i] == lastElementMirror' is false");
        Result result14 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem14, result14));

        return testData;
    }

    /**
     * Among the mutants inside main/mutants folder, there are 2 not equivalent mutants that pass all the tests:
     * - Problem_solve_ROR_32, which can be killed by a test that contains the number 0 among the elements of the array
     * - Problem_solve_ROR_39, which can be killed by a test that should return false, but the last number is the reverse
     * of itself
     */
    public List<Pair<Problem, Result>> getAdditionalTestDataToKillMutants() {

        List<Pair<Problem, Result>> testData = new ArrayList<Pair<Problem, Result>>();

        Problem problem1 = new Problem(4, new int[] {0, 1, 2, 12}, "Kill mutant Problem_solve_ROR_32");
        Result result1= Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem1, result1));

        Problem problem2 = new Problem(4, new int[] {1, 2, 3, 121}, "Kill mutant Problem_solve_ROR_39");
        Result result2 = Result.makeNormalResult(false);
        testData.add(new Pair<Problem, Result>(problem2, result2));

        return testData;
    }
}
