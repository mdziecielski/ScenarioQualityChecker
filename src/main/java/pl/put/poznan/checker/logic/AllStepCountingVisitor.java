package pl.put.poznan.checker.logic;

/**
 * Counts number of steps
 */
public class AllStepCountingVisitor implements ScenarioVisitor {
    int stepCount = 0;

    /**
     * Visit step without subscenario
     * Increment stepCount
     * @param simpleStep A SimpleStep Class object, step without sub-scenario
     */
    @Override
    public void visit(SimpleStep simpleStep) {
        stepCount += 1;
    }

    /**
     * Visit step with subscenario
     * Increment stepCount
     * @param complexStep A ComplexStep Class object, step containing a sub-scenario
     */
    @Override
    public void visit(ComplexStep complexStep) {
        for (Step st : complexStep.subscenario) {
            st.accept(this);
        }
        stepCount += 1;
    }

    /**
     * Begins visit for mainScenario and calls visit method for every step
     * @param mainScenario Scenario to be analysed
     */
    @Override
    public void visit(MainScenario mainScenario) {
        for (Step step : mainScenario.getSteps()) {
            step.accept(this);
        }
    }
}
