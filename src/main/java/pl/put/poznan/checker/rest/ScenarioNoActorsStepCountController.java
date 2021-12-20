package pl.put.poznan.checker.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.put.poznan.checker.app.ScenarioQualityCheckerApplication;
import pl.put.poznan.checker.logic.*;
// import ch.qos.logback.core.net.ObjectWriter;

import java.util.Arrays;

@RestController
@RequestMapping(value = { "/noActorStepCount" })

public class ScenarioNoActorsStepCountController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioNoActorsStepCountController.class);
    private final ScenarioQualityChecker scenarioQualityChecker;

    @Autowired
    public ScenarioNoActorsStepCountController(ScenarioQualityChecker scenarioQualityChecker) {
        this.scenarioQualityChecker = scenarioQualityChecker;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public WithoutActorsStepOutput get(@PathVariable(value = "id", required = false) String id,
                               @RequestParam(value = "checks", defaultValue = "upper,escape") String[] checks) {
        return new WithoutActorsStepOutput(scenarioQualityChecker.countNoActorSteps());
    }
}
