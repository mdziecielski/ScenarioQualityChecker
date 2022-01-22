package pl.put.poznan.checker.logic;

public class DepthCountingVisitor implements ScenarioVisitor {
    int depthCount = 1;

    @Override
    public void visit(SimpleStep simpleStep) { }

    @Override
    public void visit(ComplexStep complexStep) {
        for (Step st : complexStep.subscenario) {
            st.accept(this);
        }
        depthCount += 1;
    }

    @Override
    public void visit(MainScenario mainScenario) {
        for (Step step : mainScenario.getSteps()) {
            step.accept(this);
        }
    }
}