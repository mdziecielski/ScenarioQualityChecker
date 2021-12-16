package pl.put.poznan.checker.logic;

public interface ScenarioVisitor {
    int visit(SimpleStep simpleStep);

    int visit(ComplexStep complexStep);
}