package pl.put.poznan.checker.logic;

/**
 * Visitor which will count the total number of steps which start from a keyword.
 */

public class KeywordStepCountingVisitor implements ScenarioVisitor {
    /**
     * List of keywords to look for.
     */
    private final String[] keywords = {"ELSE", "IF", "FOR EACH"};

    /**
     * Total count of steps containing any keyword.
     */
    public int stepCount = 0;

    /**
     * Checks weather certain text (String) begins with any of the defined keywords.
     * @param text Text of a step to check.
     * @return Returns True when text begins with defined keywords, false otherwise
     */
    public boolean  checkIfBeginWithKeyword(String text){
        for (String keyword : this.keywords){
            if (text.startsWith(keyword)){
                return true;
            }
        }
        return false;
    }

    /**
     * For a SimpleStep Class Object, this method will increment stepCount by one
     * when step starts with any of the defined keywords.
     * @param simpleStep A SimpleStep Class object, step without sub-scenario
     */

    @Override
    public void visit(SimpleStep simpleStep) {
        if (checkIfBeginWithKeyword(simpleStep.text)){
            this.stepCount += 1;
        }
    }

    /**
     * For a ComplexStep Class Object, this method will increment stepCount by one
     * when step start with any of the defined keywords and visit all the steps in a sub-scenario.
     * @param complexStep A ComplexStep Class object, step containing a sub-scenario
     */

    @Override
    public void visit(ComplexStep complexStep) {
        if (checkIfBeginWithKeyword(complexStep.text)){
            this.stepCount += 1;
        }

        for (Step step : complexStep.subscenario){
            step.accept(this);
        }
    }

    /**
     * For a MainScenario Class Object, this method will visit all the
     * steps of a scenario.
     * @param mainScenario Scenario to be analysed
     */

    @Override
    public void visit(MainScenario mainScenario) {
        for (Step step : mainScenario.steps){
            step.accept(this);
        }
    }
}
