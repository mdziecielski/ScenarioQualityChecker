package pl.put.poznan.checker.logic;

/**
 * Interface used by Visitor classes.
 * Certain Visitors will be used to conduct actions on Scenario, and it's subtypes.
 */

public interface ScenarioVisitor {
    /**
     * This method defines actions conducted on SimpleStep Class Objects.
     * @param simpleStep A SimpleStep Class object, step without sub-scenario
     */
    void visit(SimpleStep simpleStep);

    /**
     * This method defines actions conducted on ComplexStep Class Objects.
     * @param complexStep A ComplexStep Class object, step containing a sub-scenario
     */
    void visit(ComplexStep complexStep);

    /**
     * This method defines actions conducted on MainScenario Class Obejcts.
     * @param mainScenario Scenario to be analysed
     */
    void visit(MainScenario mainScenario);
}