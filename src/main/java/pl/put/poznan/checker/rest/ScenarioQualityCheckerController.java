package pl.put.poznan.checker.rest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.checker.logic.ScenarioQualityChecker;
import pl.put.poznan.checker.logic.TestClass;

import java.util.Arrays;


@RestController
@RequestMapping(value = {"/getScenarioName/{id}", "/getScenarioName"})

public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable(value = "id", required = false) String id,
                              @RequestParam(value="checks", defaultValue="upper,escape") String[] checks) {

        // log the parameters
        logger.debug(id);
        logger.debug(Arrays.toString(checks));

        // perform the transformation, you should run your logic here, below is just a silly example
        ScenarioQualityChecker checker = new ScenarioQualityChecker(checks);
        return checker.checks(id + " get");
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public TestClass post(@PathVariable(value = "id", required = false) String id,
                          @RequestBody TestClass checks) {

        // log the parameters
        logger.debug(id);
        logger.debug(checks.name);

        // perform the transformation, you should run your logic here, below is just a silly example
//        ScenarioQualityChecker checker = new ScenarioQualityChecker(checks);
//        return checker.checks(text + " post");
        return checks;
    }



}


