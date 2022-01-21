package pl.put.poznan.checker.logic;
import java.util.HashMap;

public class ActorsOccurencesCounterVisitor implements ScenarioVisitor{
    public HashMap<String,Integer> actorsOccurences = new HashMap<String,Integer>();

    @Override
    public void visit(SimpleStep simpleStep) {
        for (String key:actorsOccurences.keySet()){
            if(simpleStep.text.contains(key)){
                actorsOccurences.put(key,actorsOccurences.get(key)+1);
            }
        }
    }

    @Override
    public void visit(ComplexStep complexStep) {
        for (Step st : complexStep.subscenario) {
            st.accept(this);
        }

        for (String key:actorsOccurences.keySet()){
            if(complexStep.text.contains(key)){
                actorsOccurences.put(key,actorsOccurences.get(key)+1);
            }
        }
    }

    @Override
    public void visit(MainScenario mainScenario) {
        for (String s: mainScenario.actors)
            actorsOccurences.put(s,0);
        actorsOccurences.put(mainScenario.systemActor,0);
        for (Step step : mainScenario.steps) {
            step.accept(this);
        }
    }
}
