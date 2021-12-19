package pl.put.poznan.checker.logic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WithoutActorAtFirstPlaceStepCountingVisitor implements ScenarioVisitor {

    private String[] actors;

    @Override
    public int visit(SimpleStep simpleStep) {
        String[] Words = simpleStep.text.split(" ");
        String MaybeActor = Words[0];
        if(!Arrays.asList(actors).contains(MaybeActor)) {
            return 1;
        }
        else return 0;
    }

    @Override
    public int visit(ComplexStep complexStep) {
        String[] Words = complexStep.text.split(" ");
        String MaybeActor = Words[0];
        int stepCount = 0;
        for (Step st : complexStep.subscenario) {
            stepCount += st.accept(this);
        }
        if(!Arrays.asList(actors).contains(MaybeActor)) {
            return stepCount+1;
        }
        else return stepCount;
    }

    @Override
    public int visit(MainScenario mainScenario) {
        actors = mainScenario.actors;
        int stepCount = 0;
        for (Step step : mainScenario.steps) {
            stepCount += step.accept(this);
        }
        return stepCount;
    }
}
