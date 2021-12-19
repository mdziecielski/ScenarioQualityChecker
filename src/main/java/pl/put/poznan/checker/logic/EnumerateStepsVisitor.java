package pl.put.poznan.checker.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EnumerateStepsVisitor implements ScenarioVisitor {
    public Collection<EnumeratedStep> enumeratedSteps = new LinkedList<>();
    public List<Integer> currentEnum = new ArrayList<>();

    @Override
    public void visit(SimpleStep simpleStep) {
        String stepCount = "";

        for (var x : currentEnum) {
            stepCount += Integer.toString(x) + ".";
        }
        currentEnum.set(currentEnum.size() - 1, currentEnum.get(currentEnum.size() - 1) + 1);

        enumeratedSteps.add(new EnumeratedStep(stepCount, simpleStep.text));
    }

    @Override
    public void visit(ComplexStep complexStep) {
        String stepCount = "";

        for (var x : currentEnum) {
            stepCount += Integer.toString(x) + ".";
        }

        currentEnum.add(1);

        enumeratedSteps.add(new EnumeratedStep(stepCount, complexStep.text));

        for (var st : complexStep.subscenario) {
            st.accept(this);
        }
        currentEnum.remove(currentEnum.size() - 1);
    }

    @Override
    public void visit(MainScenario mainScenario) {
        currentEnum.add(1);
        for (var st : mainScenario.steps) {
            st.accept(this);
        }
        currentEnum.remove(currentEnum.size() - 1);

    }
}
