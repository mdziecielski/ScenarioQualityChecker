package pl.put.poznan.checker.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import pl.put.poznan.checker.app.ScenarioQualityCheckerApplication;
import pl.put.poznan.checker.logic.EnumerateStepsVisitor;
import pl.put.poznan.checker.logic.MainScenario;
// import ch.qos.logback.core.net.ObjectWriter;
import pl.put.poznan.checker.logic.ScenarioQualityChecker;
import pl.put.poznan.checker.logic.ScenarioVisitor;
import pl.put.poznan.checker.logic.StepCountOutput;

import java.util.Arrays;

@RestController
@Validated
@RequestMapping(value = { "/customDepth" })

public class ScenarioCustomDepthController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioEnumerateStepsController.class);
    private final ScenarioQualityChecker scenarioQualityChecker;

    @Autowired
    public ScenarioCustomDepthController(ScenarioQualityChecker scenarioQualityChecker) {
        this.scenarioQualityChecker = scenarioQualityChecker;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity get(@PathVariable(value = "id", required = false) String id,
                              @RequestParam(value = "checks", defaultValue = "upper,escape") String[] checks,
                              @RequestParam(value = "depth", defaultValue = "1") int depth) {
        logger.info("Received a request to /customDepth");
        try {
            return new ResponseEntity<>(scenarioQualityChecker.createCustomDepthScenario(depth), HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("No scenario was loaded");
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
