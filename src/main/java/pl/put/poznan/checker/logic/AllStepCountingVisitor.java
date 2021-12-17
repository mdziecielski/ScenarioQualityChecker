package pl.put.poznan.checker.logic;

public class AllStepCountingVisitor implements ScenarioVisitor {
    @Override
    public int visit(SimpleStep simpleStep) {
        return 1;
    }

    @Override
    public int visit(ComplexStep complexStep) {
        int stepCount = 0;
        for (Step st : complexStep.subscenario) {
            stepCount += st.accept(this);
        }
        return stepCount + 1;
    }

    @Override
    public int visit(MainScenario mainScenario) {
        int stepCount = 0;

        for (Step step : mainScenario.steps) {
            stepCount += step.accept(this);
        }
        return stepCount;
    }
}
