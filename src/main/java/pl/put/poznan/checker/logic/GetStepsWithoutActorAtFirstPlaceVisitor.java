package pl.put.poznan.checker.logic;
import java.util.ArrayList;

/**
 * Creates list of steps without actor at first place
 */
public class GetStepsWithoutActorAtFirstPlaceVisitor implements ScenarioVisitor {
    KeywordStepCountingVisitor keyWord = new KeywordStepCountingVisitor();
    ArrayList<String> actors = new ArrayList<String>();
    ArrayList<String> noActorSteps = new ArrayList<String>();

    /**
     * Adds simpleStep to the list of steps if actor is not present at the first place
     * @param simpleStep step without subscenario
     */
    @Override
    public void visit(SimpleStep simpleStep) {
        String[] Words = simpleStep.text.split(" ");
        String MaybeActor = Words[0];
        if (!actors.contains(MaybeActor) && !keyWord.checkIfBeginWithKeyword(simpleStep.text)){
            noActorSteps.add(simpleStep.text);
        }
    }

    /**
     * Adds complexStep to the list of steps if actor is not present at the first place
     * and calls visit method for subscenario
     * @param complexStep step with subscenario
     */
    @Override
    public void visit(ComplexStep complexStep) {
        String[] Words = complexStep.text.split(" ");
        String MaybeActor = Words[0];
        for (Step st : complexStep.subscenario) {
            st.accept(this);
        }
        if (!actors.contains(MaybeActor) && !keyWord.checkIfBeginWithKeyword(complexStep.text)){
            noActorSteps.add(complexStep.text);
        }
    }

    /**
     * Create list of actors and calls visit method for every step in mainScenario
     * @param mainScenario class instance representing scenario
     */
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
