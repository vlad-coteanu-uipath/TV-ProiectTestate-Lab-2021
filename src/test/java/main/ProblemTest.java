package main;


import javafx.util.Pair;
import main.utilities.Result;
import main.utilities.ResultType;
import main.utilities.TestDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProblemTest {

    private static TestDataProvider testDataProvider;

    @BeforeAll
    public static void initializeUtilities() {
        testDataProvider = new TestDataProvider();
    }

    @TestFactory
    public Iterable<DynamicTest> equivalencePartitionTest() {
        return prepareDynamicTestData(testDataProvider.getEquivalencePartitioningTestData());
    }

    @TestFactory
    public Iterable<DynamicTest> boundaryAnalysisTest() {
        return prepareDynamicTestData(testDataProvider.getBoundaryAnalysisTestData());
    }

    @TestFactory
    public Iterable<DynamicTest> causeEffectGraph() {
        return prepareDynamicTestData(testDataProvider.getCauseEffectGraphingTestData());
    }

    @TestFactory
    public Iterable<DynamicTest> modifiedConditionDecisionCoverage() {
        return prepareDynamicTestData(testDataProvider.getModifiedConditionDecisionCoverageTestData());
    }

    private Iterable<DynamicTest> prepareDynamicTestData(List<Pair<Problem, Result>> testData) {
        return testData.stream().map(pair -> {
            Problem p = pair.getKey();
            Result r = pair.getValue();
            return DynamicTest.dynamicTest(p.toString(), () -> {
                if(r.getResultType() == ResultType.EXCEPTION) {
                    Exception exception = Assertions.assertThrows(RuntimeException.class, p::solve);
                    Assert.assertEquals(r.getErrorMessage(), exception.getMessage());
                } else {
                    Assert.assertEquals(r.getValue(), p.solve());
                }
            });
        }).collect(Collectors.toList());
    }

}
