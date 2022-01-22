package pl.put.poznan.checker.logic;

import java.util.Collection;
import java.util.LinkedList;

public class CustomDepthVisitor implements ScenarioVisitor {
    private int depth;
    private int currentDepth = 1;
    public Collection<CustomStep> customSteps = new LinkedList<>();

    public CustomDepthVisitor(int depth) {
        this.depth = depth;
    }

    @Override
    public void visit(SimpleStep simpleStep) {
        customSteps.add(new CustomStep(simpleStep.text));
    }

    @Override
    public void visit(ComplexStep complexStep) {
        customSteps.add(new CustomStep(complexStep.text));

        if (currentDepth < depth) {
            currentDepth++;
            for (Step step : complexStep.subscenario) {
                step.accept(this);
            }
            currentDepth--;
        }
    }

    @Override
    public void visit(MainScenario mainScenario) {
        for (Step step: mainScenario.getSteps()) {
            step.accept(this);
        }
    }
}
