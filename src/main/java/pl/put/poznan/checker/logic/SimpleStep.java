package pl.put.poznan.checker.logic;

public class SimpleStep implements Step {
    public String text;

    @Override
    public int accept(ScenarioVisitor visitor) {
        return visitor.visit(this);
    }
}
