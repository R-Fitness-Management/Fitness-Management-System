package com.fitness.management;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Path to the feature file
    glue = "com.fitness.management.steps",    // Path to step definitions
    plugin = {"pretty", "html:target/cucumber-reports.html"} // Generate HTML report
)
public class CucumberTestRunner {
}
