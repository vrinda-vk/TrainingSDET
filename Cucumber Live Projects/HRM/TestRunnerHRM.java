package cucumberTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/Features",
		glue = {"stepDefinitions"},
		tags = "@HRM",
		plugin = {"html:target/test-reports/test-report-HRM_AllActivities.html"},
	    monochrome = true
		)

public class TestRunnerHRM {
// knowingly empty
}
