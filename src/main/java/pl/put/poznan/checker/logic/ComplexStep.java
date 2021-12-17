package pl.put.poznan.checker.logic;

public class ComplexStep implements Step {
    public String text;
    public Step[] subscenario;

    @Override
    public int accept(ScenarioVisitor visitor) {
        return visitor.visit(this);
    }
}