package cucumberTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/Features",
		glue = {"stepDefinitions"},
		tags = "@CRM",
		plugin = {"html:target/test-reports/test-report-CRM_AllActivities.html"},
	    monochrome = true
		)

public class TestRunnerCRM {
// knowingly empty
}
