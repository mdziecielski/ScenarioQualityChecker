package pl.put.poznan.checker.logic;

public interface ScenarioVisitor {
    int visitSimpleStep(SimpleStep simpleStep);
    int visitComplexStep(ComplexStep complexStep);
}