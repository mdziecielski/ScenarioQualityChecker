package pl.put.poznan.checker.logic;

import java.util.List;

public class ComplexStep implements Step {
    public String text;
    public Step[] subscenario;

    public void setText(String text) {
        this.text = text;
    }

    public void setSubscenario(List<Step> subscenario) {
        this.subscenario = subscenario.toArray(new Step[0]);
    }

    @Override
    public void accept(ScenarioVisitor visitor) {
        visitor.visit(this);
    }
}