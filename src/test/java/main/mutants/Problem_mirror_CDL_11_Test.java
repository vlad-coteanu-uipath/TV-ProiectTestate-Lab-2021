package main.mutants;


import javafx.util.Pair;
import main.Problem;
import main.utilities.Result;
import main.utilities.ResultType;
import main.utilities.TestDataProvider;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Problem_mirror_CDL_11_Test {

    private static TestDataProvider testDataProvider;

    @BeforeAll
    public static void initializeUtilities() {
        testDataProvider = new TestDataProvider();
    }

    @TestFactory
    public Iterable<DynamicTest> mutant_Problem_mirror_CDL_11_equivalence_partitioning() {
        return prepareDynamicTestData(testDataProvider.getEquivalencePartitioningTestData().stream()
                .map(p -> new Pair<Problem, Result>(new Problem_mirror_CDL_11(p.getKey()), p.getValue())).collect(Collectors.toList()));
    }

    @TestFactory
    public Iterable<DynamicTest> mutant_Problem_mirror_CDL_11_boundary_values() {
        return prepareDynamicTestData(testDataProvider.getBoundaryAnalysisTestData().stream()
                .map(p -> new Pair<Problem, Result>(new Problem_mirror_CDL_11(p.getKey()), p.getValue())).collect(Collectors.toList()));
    }

    @TestFactory
    public Iterable<DynamicTest> mutant_Problem_mirror_CDL_11_cause_effect_graph() {
        return prepareDynamicTestData(testDataProvider.getCauseEffectGraphingTestData().stream()
                .map(p -> new Pair<Problem, Result>(new Problem_mirror_CDL_11(p.getKey()), p.getValue())).collect(Collectors.toList()));
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
