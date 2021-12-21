package pl.put.poznan.checker.logic;
import java.util.ArrayList;

public class GetStepsWithoutActorAtFirstPlaceVisitor implements ScenarioVisitor {
    int stepCount = 0;
    KeywordStepCountingVisitor KeyWord = new KeywordStepCountingVisitor();
    ArrayList<String> actors = new ArrayList<String>();
    ArrayList<String> problematic = new ArrayList<String>();

    @Override
    public void visit(SimpleStep simpleStep) {
        String[] Words = simpleStep.text.split(" ");
        String MaybeActor = Words[0];
        if (!actors.contains(MaybeActor) && !KeyWord.checkIfBeginWithKeyword(simpleStep.text)){
            problematic.add(simpleStep.text);
        }
    }

    @Override
    public void visit(ComplexStep complexStep) {
        String[] Words = complexStep.text.split(" ");
        String MaybeActor = Words[0];
        for (Step st : complexStep.subscenario) {
            st.accept(this);
        }
        if (!actors.contains(MaybeActor) && !KeyWord.checkIfBeginWithKeyword(complexStep.text)){
            problematic.add(complexStep.text);
        }
    }

    @Override
    public void visit(MainScenario mainScenario) {
        for (String s: mainScenario.actors)
            actors.add(s);
        actors.add(mainScenario.systemActor);
        for (Step step : mainScenario.steps) {
            step.accept(this);
        }
    }
}
