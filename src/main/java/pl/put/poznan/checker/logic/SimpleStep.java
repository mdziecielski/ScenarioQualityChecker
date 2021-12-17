package pl.put.poznan.checker.logic;

public class SimpleStep implements Step {
    public String text;

    @Override
    public void accept(ScenarioVisitor visitor) {
        visitor.visit(this);
    }
}
