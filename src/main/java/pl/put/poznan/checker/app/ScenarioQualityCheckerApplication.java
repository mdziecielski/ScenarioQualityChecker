package pl.put.poznan.checker.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.put.poznan.checker.logic.MainScenario;
import pl.put.poznan.checker.logic.ScenarioQualityChecker;

@SpringBootApplication(scanBasePackages = { "pl.put.poznan.checker.rest" })
public class ScenarioQualityCheckerApplication {

    @Bean
    public ScenarioQualityChecker scenarioQualityBean() {
        return new ScenarioQualityChecker();
    }

    public static void main(String[] args) {
        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }

}
