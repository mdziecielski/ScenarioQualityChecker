package pl.put.poznan.checker.logic;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Creates scenario with custom depth (provided by user)
 */
public class CustomDepthVisitor implements ScenarioVisitor {
    private int depth;
    private int currentDepth = 1;
    public Collection<CustomStep> customSteps = new LinkedList<>();

    public CustomDepthVisitor(int depth) {
        this.depth = depth;
    }

    /**
     * Visit step without subscenario
     * @param simpleStep A SimpleStep Class object, step without sub-scenario
     */
    @Override
    public void visit(SimpleStep simpleStep) {
        customSteps.add(new CustomStep(simpleStep.text));
    }

    /**
     * Visit step with subscenario
     * Change value of currentDepth accordingly
     * @param complexStep A ComplexStep Class object, step containing a sub-scenario
     */
    @Override
    public void visit(ComplexStep complexStep) {
        customSteps.add(new CustomStep(complexStep.text));

        if (currentDepth < depth) {
            currentDepth++;
            for (Step step : complexStep.subscenario) {
                step.accept(this);
            }
            currentDepth--;
        }
    }

    /**
     * Begins visit for mainScenario and calls visit method for every step
     * @param mainScenario class instance representing scenario
     */
    @Override
    public void visit(MainScenario mainScenario) {
        for (Step step: mainScenario.getSteps()) {
            step.accept(this);
        }
    }
}
