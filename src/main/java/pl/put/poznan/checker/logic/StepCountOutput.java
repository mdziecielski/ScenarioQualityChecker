package pl.put.poznan.checker.logic;

import java.util.ArrayList;

public class StepCountOutput {
    public int stepCount;
    public ArrayList<String> stepsWithoutActors;

    public StepCountOutput(int st) {
        stepCount = st;
    }
    public StepCountOutput(ArrayList<String> st) {
        stepsWithoutActors = st;
    }
}
