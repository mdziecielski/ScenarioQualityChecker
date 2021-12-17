package pl.put.poznan.checker.logic;

public class ComplexStep implements Step {
    public String text;
    public Step[] subscenario;

    @Override
    public void accept(ScenarioVisitor visitor) {
        visitor.visit(this);
    }
}