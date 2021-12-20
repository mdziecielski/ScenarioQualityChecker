package pl.put.poznan.checker.logic;
import java.util.Arrays;

public class WithoutActorAtFirstPlaceStepCountingVisitor implements ScenarioVisitor {
    int stepCount = 0;
    String actors[];

    @Override
    public void visit(SimpleStep simpleStep) {
        String[] Words = simpleStep.text.split(" ");
        String MaybeActor = Words[0];
        if (!Arrays.asList(actors).contains(MaybeActor)){
            stepCount += 1;
        }
    }

    @Override
    public void visit(ComplexStep complexStep) {
        String[] Words = complexStep.text.split(" ");
        String MaybeActor = Words[0];
        for (Step st : complexStep.subscenario) {
            st.accept(this);
        }
        if (!Arrays.asList(actors).contains(MaybeActor)){
            stepCount += 1;
        }
    }

    @Override
    public void visit(MainScenario mainScenario) {
        actors = mainScenario.actors;
        for (Step step : mainScenario.steps) {
            step.accept(this);
        }
    }
}
