package pl.put.poznan.checker.logic;

public class WithoutActorAtFirstPlaceStepCountingVisitor implements ScenarioVisitor{
    @Override
    public int visitSimpleStep(SimpleStep simpleStep) {
        return 0;
    }

    @Override
    public int visitComplexStep(ComplexStep complexStep) {
        return 0;
    }
}
