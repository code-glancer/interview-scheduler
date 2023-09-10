package com.code.glancer.interview.scheduler.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = "com.code.glancer.interview.scheduler.bdd", // Package where your step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports"} // Optional: Define plugins for reporting
)
public class CandidateTestRunner {

}
