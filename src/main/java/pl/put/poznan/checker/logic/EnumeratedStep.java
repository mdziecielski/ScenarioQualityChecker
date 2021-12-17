package pl.put.poznan.checker.logic;

public class EnumeratedStep {
    public int stepNo;
    public SimpleStep step;

    public EnumeratedStep(int n, SimpleStep s) {
        stepNo = n;
        step = s;
    }
}
