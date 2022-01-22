package pl.put.poznan.checker.logic;

public class AllStepCountingVisitor implements ScenarioVisitor {
    int stepCount = 0;

    @Override
    public void visit(SimpleStep simpleStep) {
        stepCount += 1;
    }

    @Override
    public void visit(ComplexStep complexStep) {
        for (Step st : complexStep.subscenario) {
            st.accept(this);
        }
        stepCount += 1;
    }

    @Override
    public void visit(MainScenario mainScenario) {
        for (Step step : mainScenario.getSteps()) {
            step.accept(this);
        }
    }
}
