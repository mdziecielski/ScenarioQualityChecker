package pl.put.poznan.checker.logic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is just an example to show that the logic should be outside the REST
 * service.
 */
public class ScenarioQualityChecker {

    // private final String[] checks;

    private MainScenario scenario;

    public int countAllSteps() {
        AllStepCountingVisitor visitor = new AllStepCountingVisitor();
        scenario.accept(visitor);
        return visitor.stepCount;
    }

    public int countKeywordSteps() {
        KeywordStepCountingVisitor visitor = new KeywordStepCountingVisitor();
        scenario.accept(visitor);
        return visitor.stepCount;
    }

    public ArrayList<String> countNoActorSteps() {
        WithoutActorAtFirstPlaceStepCountingVisitor visitor = new WithoutActorAtFirstPlaceStepCountingVisitor();
        scenario.accept(visitor);
        return visitor.problematic;
    }

    public String enumerateSteps() {
        EnumerateStepsVisitor visitor = new EnumerateStepsVisitor();
        scenario.accept(visitor);
        String enumeratedScenarioString = "";
        for (var estep : visitor.enumeratedSteps) {
            enumeratedScenarioString += estep.stepNo + estep.step + '\n';
        }
        return enumeratedScenarioString;
    }

    public MainScenario loadInputData(MainScenario scenarioData) {
        this.scenario = scenarioData;
        return this.scenario;
    }
}
