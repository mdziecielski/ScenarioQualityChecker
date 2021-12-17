package pl.put.poznan.checker.logic;

public class WithoutActorAtFirstPlaceStepCountingVisitor implements ScenarioVisitor {
    @Override
    public void visit(SimpleStep simpleStep) {
        // return 0;
    }

    @Override
    public void visit(ComplexStep complexStep) {
        // return 0;
    }

    @Override
    public void visit(MainScenario mainScenario) {
        // return 0;
    }
}
