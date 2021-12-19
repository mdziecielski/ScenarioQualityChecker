package pl.put.poznan.checker.logic;

public interface ScenarioVisitor {
    void visit(SimpleStep simpleStep);

    void visit(ComplexStep complexStep);

    void visit(MainScenario mainScenario);
}