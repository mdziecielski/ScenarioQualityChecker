package pl.put.poznan.checker.logic;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public interface Step {
    int accept(ScenarioVisitor visitor);
}
