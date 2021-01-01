package cucumberTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/Features",
		glue = {"stepDefinitions"},
		tags = "@jobBoard",
		plugin = {"html:target/test-reports/test-report-JobBoard_AllActivities.html"},
	    monochrome = true
		)

public class TestRunnerJB {
// knowingly empty
}
