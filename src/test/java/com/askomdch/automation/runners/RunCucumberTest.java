package com.askomdch.automation.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.askomdch.automation.steps", "com.askomdch.automation.hooks"},
    plugin = {"pretty", "html:target/cucumber-report.html"},
    tags = "@ui"
)
public class RunCucumberTest {}
