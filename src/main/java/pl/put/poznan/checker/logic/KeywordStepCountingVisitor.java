package pl.put.poznan.checker.logic;

public class KeywordStepCountingVisitor implements ScenarioVisitor {
    private String[] keywords = {"ELSE", "IF", "FOR EACH"};
    public int stepCount = 0;

    public boolean  checkIfBeginWithKeyword(String text){
        String[] splitStep = text.split("[ :]");

        for (String keyword : this.keywords){
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
    public void visit(SimpleStep simpleStep) {
        if (checkIfBeginWithKeyword(simpleStep.text)){
            this.stepCount += 1;
        }
    }

    @Override
    public void visit(ComplexStep complexStep) {
        if (checkIfBeginWithKeyword(complexStep.text)){
            this.stepCount += 1;
        }

        for (Step step : complexStep.subscenario){
            step.accept(this);
        }
    }

    @Override
    public void visit(MainScenario mainScenario) {
        for (Step step : mainScenario.steps){
            step.accept(this);
        }
    }
}
