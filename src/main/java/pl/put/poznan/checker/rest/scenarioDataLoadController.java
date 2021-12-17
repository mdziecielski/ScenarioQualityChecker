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
import pl.put.poznan.checker.logic.MainScenario;
// import ch.qos.logback.core.net.ObjectWriter;
import pl.put.poznan.checker.logic.ScenarioDataInput;
import pl.put.poznan.checker.logic.ScenarioQualityChecker;
import pl.put.poznan.checker.logic.ScenarioVisitor;
import pl.put.poznan.checker.logic.TestClass;

import java.util.Arrays;

@RestController
@RequestMapping(value = { "/scenarioDataLoad/{id}", "/scenarioDataLoad" })

public class scenarioDataLoadController {

    private static final Logger logger = LoggerFactory.getLogger(scenarioDataLoadController.class);
    private final ScenarioQualityChecker scenarioQualityChecker;

    @Autowired
    public scenarioDataLoadController(ScenarioQualityChecker scenarioQualityChecker) {
        this.scenarioQualityChecker = scenarioQualityChecker;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable(value = "id", required = false) String id,
            @RequestParam(value = "checks", defaultValue = "upper,escape") String[] checks) {

        // log the parameters
        // logger.debug(id);
        // logger.debug(Arrays.toString(checks));
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public MainScenario post(@PathVariable(value = "id", required = false) String id,
            @RequestBody MainScenario scenarioData) throws JsonProcessingException {

        return scenarioQualityChecker.loadInputData(scenarioData);
    }

}
