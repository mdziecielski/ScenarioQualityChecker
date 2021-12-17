package pl.put.poznan.checker.logic;

/**
 * Scenario
 */
public class MainScenario {

    public String title;
    public String[] actors;
    public String systemActor;

    public Step[] steps;

    public int accept(ScenarioVisitor visitor) {
        return visitor.visit(this);
    }
}