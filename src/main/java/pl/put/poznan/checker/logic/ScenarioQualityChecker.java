package pl.put.poznan.checker.logic;

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

    public void enumerateSteps() {
        scenario.accept(new EnumerateStepsVisitor());
    }

    public MainScenario loadInputData(MainScenario scenarioData) {
        this.scenario = scenarioData;
        return this.scenario;
    }
}
