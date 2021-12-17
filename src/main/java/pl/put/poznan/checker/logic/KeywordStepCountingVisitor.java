package pl.put.poznan.checker.logic;

public class KeywordStepCountingVisitor implements ScenarioVisitor {
    @Override
    public int visit(SimpleStep simpleStep) {
        return 0;
    }

    @Override
    public int visit(ComplexStep complexStep) {
        return 0;
    }

    @Override
    public int visit(MainScenario mainScenario) {
        return 0;
    }
}
