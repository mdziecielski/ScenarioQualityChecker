package pl.put.poznan.checker.logic;

public class KeywordStepCountingVisitor implements ScenarioVisitor {

    public String[] keywords = {"ELSE", "IF", "FOR EACH"};

    private boolean checkIfbeginsWithKeyword (String text) {
        String[] splitStep = text.split("[ :]");

        for (String keyword : this.keywords) {
            if (splitStep[0].equals(keyword)){

                return true;
            }

            if ((splitStep[0] + " " + splitStep[1]).equals(keyword)){

                return true;
            }
        }

        return false;
    }


    @Override
    public int visit(SimpleStep simpleStep) {
        if (checkIfbeginsWithKeyword(simpleStep.text)){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public int visit(ComplexStep complexStep) {
        int stepCount = 0;

        for (Step st : complexStep.subscenario) {
            stepCount += st.accept(this);
        }

        if (checkIfbeginsWithKeyword(complexStep.text)){
            stepCount += 1;
        }

        return stepCount;
    }

    @Override
    public int visit(MainScenario mainScenario) {
        int stepCount = 0;

        for (Step step : mainScenario.steps) {
            stepCount += step.accept(this);
        }
        return stepCount;
    }
}
