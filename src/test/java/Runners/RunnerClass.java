package Runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/Features",
        plugin = {"pretty", "html:target/htmlReports/cucumber-report.html"},
        glue= {"Prices"},
        tags = {"@Price"}
        )

public class RunnerClass {
}
