package pl.put.poznan.checker.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.put.poznan.checker.app.ScenarioQualityCheckerApplication;
import pl.put.poznan.checker.logic.MainScenario;
// import ch.qos.logback.core.net.ObjectWriter;
import pl.put.poznan.checker.logic.ScenarioQualityChecker;
import pl.put.poznan.checker.logic.ScenarioVisitor;
import pl.put.poznan.checker.logic.StepCountOutput;

import java.util.Arrays;

@RestController
@RequestMapping(value = { "/allStepCount" })

public class ScenarioAllStepCountController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioAllStepCountController.class);
    private final ScenarioQualityChecker scenarioQualityChecker;

    @Autowired
    public ScenarioAllStepCountController(ScenarioQualityChecker scenarioQualityChecker) {
        this.scenarioQualityChecker = scenarioQualityChecker;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity get(@PathVariable(value = "id", required = false) String id,
            @RequestParam(value = "checks", defaultValue = "upper,escape") String[] checks) {
        logger.info("Received a request to /allStepCount");
        try {
            return new ResponseEntity<>(new StepCountOutput(scenarioQualityChecker.countAllSteps()), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("No scenario was loaded");
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
