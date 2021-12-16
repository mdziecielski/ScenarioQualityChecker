package pl.put.poznan.checker.logic;

public class AllStepCountingVisitor implements ScenarioVisitor {
    @Override
    public int visit(SimpleStep simpleStep) {
        return 1;
    }

    @Override
    public int visit(ComplexStep complexStep) {
        int stepCount = 0;
        AllStepCountingVisitor scv = new AllStepCountingVisitor();
        for (Step st : complexStep.subscenario) {
            stepCount += st.accept(scv);
        }
        return stepCount + 1;
    }
}
