package pl.put.poznan.checker.logic;

public class WithoutActorAtFirstPlaceStepCountingVisitor implements ScenarioVisitor {
    @Override
    public int visit(SimpleStep simpleStep) {
        return 0;
    }

    @Override
    public int visit(ComplexStep complexStep) {
        return 0;
    }
}
