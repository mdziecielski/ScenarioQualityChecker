package pl.put.poznan.checker.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.mockito.internal.matchers.Null;
import pl.put.poznan.checker.logic.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ScenarioQualityCheckerTest {
    MainScenario mockScenario;
    ScenarioQualityChecker scenarioQualityChecker;
    ArrayList<Step> steps;

    @BeforeEach
    void setUp() {
        scenarioQualityChecker = new ScenarioQualityChecker();
        mockScenario = mock(MainScenario.class);
        steps = new ArrayList<Step>();

        when(mockScenario.getSteps()).thenReturn(steps);
        doCallRealMethod().when(mockScenario).accept(any(ScenarioVisitor.class));
        mockScenario.systemActor="System";
        scenarioQualityChecker.loadInputData(mockScenario);
    }

    @Test
    void testCountDepthWhileEmptyScenario() {
        assertEquals(1, this.scenarioQualityChecker.countDepth());
    }

    @Test
    void testCountDepthWhileOneLevelScenario() {
        steps.add(new SimpleStep());
        steps.add(new SimpleStep());
        steps.add(new SimpleStep());

        assertEquals(1, this.scenarioQualityChecker.countDepth());
    }

    @Test
    void testCountDepthWhileTwoLevelScenario() {
        ArrayList<Step> subscenario = new ArrayList<Step>();

        ComplexStep complexStep = new ComplexStep();
        complexStep.setSubscenario(subscenario);

        steps.add(complexStep);
        steps.add(new SimpleStep());
        steps.add(new SimpleStep());

        assertEquals(2, scenarioQualityChecker.countDepth());
    }

    @Test
    void testCountDepthWhileThreeLevelScenario() {
        ArrayList<Step> subscenario = new ArrayList<Step>();
        ArrayList<Step> subsubscenario = new ArrayList<Step>();

        ComplexStep subComplexStep = new ComplexStep();
        subscenario.add(subComplexStep);
        subComplexStep.setSubscenario(subsubscenario);

        ComplexStep complexStep = new ComplexStep();
        complexStep.setSubscenario(subscenario);

        steps.add(complexStep);
        steps.add(new SimpleStep());
        steps.add(new SimpleStep());

        assertEquals(3, scenarioQualityChecker.countDepth());
    }

    @Test
    void testCreateCustomDepthScenarioWithOneLevelScenario() {
        SimpleStep step1 = new SimpleStep();
        SimpleStep step2 = new SimpleStep();
        SimpleStep step3 = new SimpleStep();

        step1.setText("Step 1");
        step2.setText("Step 2");
        step3.setText("Step 3");

        steps.add(step1);
        steps.add(step2);
        steps.add(step3);

        String expectedResult = "Step 1\nStep 2\nStep 3\n";

        assertEquals(expectedResult, scenarioQualityChecker.createCustomDepthScenario(1));
        assertEquals(expectedResult, scenarioQualityChecker.createCustomDepthScenario(2));
        assertEquals(expectedResult, scenarioQualityChecker.createCustomDepthScenario(3));
    }

    @Test
    void testCreateCustomDepthScenarioWithTwoLevelScenario() {
        SimpleStep step1 = new SimpleStep();
        ComplexStep step2 = new ComplexStep();
        SimpleStep step21 = new SimpleStep();
        SimpleStep step3 = new SimpleStep();

        step1.setText("Step 1");
        step2.setText("Step 2");
        step21.setText("Substep 21");
        step3.setText("Step 3");

        ArrayList<Step> subscenario = new ArrayList<Step>();
        subscenario.add(step21);
        step2.setSubscenario(subscenario);

        steps.add(step1);
        steps.add(step2);
        steps.add(step3);

        String expectedResultFirstLevel = "Step 1\nStep 2\nStep 3\n";
        String expectedResultTwoLevels = "Step 1\nStep 2\nSubstep 21\nStep 3\n";

        assertEquals(expectedResultFirstLevel, scenarioQualityChecker.createCustomDepthScenario(1));
        assertEquals(expectedResultTwoLevels, scenarioQualityChecker.createCustomDepthScenario(2));
        assertEquals(expectedResultTwoLevels, scenarioQualityChecker.createCustomDepthScenario(3));
    }

    @Test
    void testAllStepCounterWithOneLevelScenario() {
        int stepcount = 5;
        for(int i = 0; i < stepcount; ++i) {
            steps.add(new SimpleStep());
        }

        assertEquals(stepcount, scenarioQualityChecker.countAllSteps());
    }

    Step generateCustomDepthStep(int depth) {
        if(depth == 1) {
            SimpleStep s = new SimpleStep();
            s.setText("test");
            return s;
        }
        ComplexStep step = new ComplexStep();
        step.setText("testDeep");
        List<Step> subscenarios = new ArrayList<Step>();
        subscenarios.add(generateCustomDepthStep(depth - 1));
        step.setSubscenario(subscenarios);
        return step;
    }

    @Test
    void testAllStepCounterWithMultiLevelScenario() {
        int stepcount = 2137;
        int depth = 2115;
        for(int i = 0; i < stepcount; ++i) {
            ComplexStep cstep = new ComplexStep();
            List<Step> subscenarios = new ArrayList<Step>();
            subscenarios.add(generateCustomDepthStep(depth));
            cstep.setSubscenario(subscenarios);
            steps.add(cstep);
        }

        assertEquals(stepcount*(depth+1), scenarioQualityChecker.countAllSteps());
    }

    @Test
    void testAllStepCounterWithEmptyScenario() {
        assertEquals(0, scenarioQualityChecker.countAllSteps());
    }

    @Test
    void testAllStepCounterWithComplexStepWithoutSimpleStepsThrowsException() {
        ComplexStep cstep = new ComplexStep();
        steps.add(cstep);
        assertThrows(NullPointerException.class, () -> {
           scenarioQualityChecker.countAllSteps();
        });
    }

    @Test
    void testEnumerateStepsWithThreeLevelDepth() {
        int stepcount = 1;
        int depth = 3;
        for(int i = 0; i < stepcount; ++i) {
            ComplexStep cstep = new ComplexStep();
            cstep.setText("testDeep");
            List<Step> subscenarios = new ArrayList<Step>();
            subscenarios.add(generateCustomDepthStep(depth));
            cstep.setSubscenario(subscenarios);
            steps.add(cstep);
        }

        assertEquals("1.testDeep\n" +
                "1.1.testDeep\n" +
                "1.1.1.testDeep\n" +
                "1.1.1.1.test\n", scenarioQualityChecker.enumerateSteps());
    }

    @Test
    void testStepsWithoutActorAtFirstWithEmptyScenario(){
        assertEquals(Collections.emptyList(),scenarioQualityChecker.countNoActorSteps());
    }

    @Test
    void testStepsWithoutActorAtFirstWithSimpleSteps(){
        SimpleStep noActor = new SimpleStep();
        SimpleStep actor = new SimpleStep();
        noActor.setText("Some text to check");
        actor.setText(mockScenario.systemActor + " doing something");
        steps.add(noActor);
        steps.add(actor);
        steps.add(actor);
        assertEquals(1,scenarioQualityChecker.countNoActorSteps().size());
    }

    @Test
    void testStepsWithoutActorAtFirstWithComplexSteps(){
        List<Step> subscenarios = new ArrayList<Step>();
        ComplexStep cstep = new ComplexStep();
        SimpleStep actorStep = new SimpleStep();
        SimpleStep noActorStep = new SimpleStep();
        actorStep.setText(mockScenario.systemActor + " doing something");
        noActorStep.setText("Some text to check");
        cstep.setText(mockScenario.systemActor + " doing something else");
        subscenarios.add(actorStep);
        subscenarios.add(actorStep);
        subscenarios.add(noActorStep);
        subscenarios.add(noActorStep);
        subscenarios.add(noActorStep);
        cstep.setSubscenario(subscenarios);
        steps.add(cstep);
        steps.add(actorStep);
        assertEquals(3,scenarioQualityChecker.countNoActorSteps().size());
    }
}