package pl.put.poznan.checker.logic;

/**
 * Counts scenario depth
 */
public class DepthCountingVisitor implements ScenarioVisitor {
    int depthCount = 1;

    /**
     * Visit step without scenario
     * Doesn't affect depthCount
     * @param simpleStep A SimpleStep Class object, step without sub-scenario
     */
    @Override
    public void visit(SimpleStep simpleStep) { }

    /**
     * Visit step with a scenario and increase depth
     * @param complexStep A ComplexStep Class object, step containing a sub-scenario
     */
    @Override
    public void visit(ComplexStep complexStep) {
        for (Step st : complexStep.subscenario) {
            st.accept(this);
        }
        depthCount += 1;
    }

    /**
     * Begins visit for mainScenario and calls visit method for every step
     * @param mainScenario class instance representing scenario
     */
    @Override
    public void visit(MainScenario mainScenario) {
        for (Step step : mainScenario.getSteps()) {
            step.accept(this);
        }
    }
}