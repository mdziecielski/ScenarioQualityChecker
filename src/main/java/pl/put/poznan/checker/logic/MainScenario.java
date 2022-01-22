package pl.put.poznan.checker.logic;

import java.util.List;

/**
 * Scenario
 */
public class MainScenario {

    public String title;
    public String[] actors;
    public String systemActor;

    public Step[] steps;

    public void accept(ScenarioVisitor visitor) {
        visitor.visit(this);
    }

    public List<Step> getSteps() {
        return List.of(this.steps);
    }
}