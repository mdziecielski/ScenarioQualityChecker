package pl.put.poznan.checker.logic;

/**
 * This is just an example to show that the logic should be outside the REST
 * service.
 */
public class ScenarioQualityChecker {

    // private final String[] checks;

    private MainScenario scenario;

    public int countAllSteps() {
        return scenario.accept(new AllStepCountingVisitor());
    }

    public int countWithoutActorSteps() {
        return scenario.accept(new WithoutActorAtFirstPlaceStepCountingVisitor());
    }

    public MainScenario loadInputData(MainScenario scenarioData) {
        this.scenario = scenarioData;
        return this.scenario;
    }
}
