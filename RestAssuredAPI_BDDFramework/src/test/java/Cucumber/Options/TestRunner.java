package Cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features", glue = "StepDefinations",
tags = "@Regression", plugin = "json:target/jsonReports/cucumber-report.json")


//C:\Users\dsharman\API-Automation\RestAssuredAPI_BDDFramework>   mvn test -Dcucumber.options="--tags @AddPlace" verify

public class TestRunner {

}
