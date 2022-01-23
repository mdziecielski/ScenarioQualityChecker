package pl.put.poznan.checker.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.checker.logic.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class KeywordStepCoutingVisitorTest {
    MainScenario mockScenario;
    ScenarioQualityChecker scenarioQualityChecker;
    ArrayList<Step> steps;

    @BeforeEach
    void setUp() {
        scenarioQualityChecker = new ScenarioQualityChecker();
        mockScenario = mock(MainScenario.class);
        steps = new ArrayList<>();

        when(mockScenario.getSteps()).thenReturn(steps);
        doCallRealMethod().when(mockScenario).accept(any(ScenarioVisitor.class));
        scenarioQualityChecker.loadInputData(mockScenario);
    }

    @Test
    void testCountKeywordsWhileEmptyScenario(){
        assertEquals(0, scenarioQualityChecker.countKeywordSteps());
    }

    @Test
    void testCountIfKeywordsWhileSingleComplexStepProvided() {
        ComplexStep complexStep = new ComplexStep();
        ArrayList <Step> subscenario = new ArrayList<>();

        complexStep.setText("IF: RandomCondition");
        complexStep.setSubscenario(subscenario);

        steps.add(complexStep);

        assertEquals(1, scenarioQualityChecker.countKeywordSteps());
    }

    @Test
    void testCountIfElseKeywordsWhileTwoComplexStepsProvided() {
        ComplexStep complexStepIf = new ComplexStep();
        ComplexStep complexStepElse = new ComplexStep();

        ArrayList <Step> subscenario = new ArrayList<>();

        complexStepIf.setText("IF: random condition");
        complexStepElse.setText("ELSE:");
        complexStepIf.setSubscenario(subscenario);
        complexStepElse.setSubscenario(subscenario);

        steps.add(complexStepIf);
        steps.add(complexStepElse);

        assertEquals(2, scenarioQualityChecker.countKeywordSteps());
    }

    @Test
    void testCountForEachKeywordsWhileSingleComplexStepsProvided(){
        ComplexStep complexStep = new ComplexStep();

        ArrayList <Step> subscenario = new ArrayList<>();

        complexStep.setText("FOR EACH: random item");
        complexStep.setSubscenario(subscenario);

        steps.add(complexStep);

        assertEquals(1, scenarioQualityChecker.countKeywordSteps());
    }

    @Test
    void testCountNestedKeywords(){
        ComplexStep firstDepthLevel = new ComplexStep();
        ComplexStep secondDepthLevel = new ComplexStep();

        ArrayList <Step> emptySubscenario = new ArrayList<>();

        secondDepthLevel.setText("IF: random condition");
        secondDepthLevel.setSubscenario(emptySubscenario);

        ArrayList <Step> nonEmptySubscenario = new ArrayList<>();
        nonEmptySubscenario.add(secondDepthLevel);

        firstDepthLevel.setText("FOR EACH: random item");
        firstDepthLevel.setSubscenario(nonEmptySubscenario);

        steps.add(firstDepthLevel);

        assertEquals(2, scenarioQualityChecker.countKeywordSteps());
    }
}