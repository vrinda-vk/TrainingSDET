package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HRMSteps {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void setup() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,10);
	}
	
	@After
	public void closeBrowser() {
		driver.quit();
	}
	
	// Activity-1 definitions below:
	
	@Given("^User is on OrangeHRM page and logged in$")
	public void userIsOnOrangeHRMPage() {
		// Visit site
		driver.get("http://alchemy.hguy.co/orangehrm");
		// Enter credentials
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		driver.findElement(By.id("btnLogin")).click();
		// Wait for dashboard page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='welcome']")));
	}
	
	@Then("^Navigate to the 'Recruitment' page$")
	public void userNavigatesToRecruitmentPage() {
		// Click on 'Recruitment' navigation menu item
		driver.findElement(By.xpath("//b[contains(.,'Recruitment')]")).click();
		driver.findElement(By.xpath("//b[contains(.,'Recruitment')]")).click();
	}
	
	@And("^Click on the 'Vacancies' menu item to navigate to the vacancies page$")
	public void userNavigatesToVacanciesMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Vacancies')]")));
		driver.findElement(By.xpath("//a[contains(.,'Vacancies')]")).click();
	}
	
	@And("^Click on the 'Add' button to navigate to the 'Add Job Vacancy' form$")
	public void userNavigatesToAddJobVacancyForm() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//b[contains(.,'Recruitment')]"))));
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[contains(.,'Vacancy')]")));
	}
	
	@And("^Fill out the necessary details on 'Add Job Vacancy' page$")
	public void fillAllDetailsOnAddJobVacancyPage() {
		// Entering Job Title via dropdown
		WebElement jobTitleElement = driver.findElement(By.xpath("//select"));
		jobTitleElement.click();
		Select jobTitleDropdown = new Select(jobTitleElement);
		jobTitleDropdown.selectByVisibleText("DevOps Engineer");
		// Entering Vacancy Name
		WebElement vacancyNameElement = driver.findElement(By.xpath("//input[contains(@id,'name')]"));
		vacancyNameElement.sendKeys("Meme Engineer 505");
		// Entering Hiring Manager via autocomplete
		driver.findElement(By.xpath("//input[@id='addJobVacancy_hiringManager']")).sendKeys("He");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> HiringManagerValues = driver.findElements(By.xpath("//li[contains(@class,'ac')]"));
		for(WebElement hrmgr:HiringManagerValues) {
			if(hrmgr.getText().equals("Hello Tester")) {
				hrmgr.click();
				break;
			}
		}
		// Entering Number of Positions
		WebElement noOfPositionsElement = driver.findElement(By.id("addJobVacancy_noOfPositions"));
		noOfPositionsElement.sendKeys("10");
		// Entering Description
		WebElement descriptionElement = driver.findElement(By.id("addJobVacancy_description"));
		descriptionElement.sendKeys("This is a test case, do not count on this vacancy!");
	}
	
	@And("^Click the 'Save' button to save the vacancy$")
	public void userClicksOnSaveVacancyButton() {
		driver.findElement(By.id("btnSave")).click();
	}
	
	@Then("^Verify that the vacancy was created$")
	public void verifyNewVacancyWasCreated() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[text()='Edit Job Vacancy']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Edit Job Vacancy']")).isDisplayed());
		System.out.println("Vacancy was posted succesfully");
	}
	
	// Activity-2 definitions below:
	
	@And("^Click on the 'Add' button to navigate to the 'Add Candidate' form$")
	public void userNavigatesToAddCandidateForm() {
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[text()='Add Candidate']")));
	}
	
	@And("^Fill out the necessary details and upload a resume$")
	public void fillAllDetailsOnAddCandidatePage() {
		// Entering Candidate Name
		driver.findElement(By.id("addCandidate_firstName")).sendKeys("Tury4");
		driver.findElement(By.id("addCandidate_middleName")).sendKeys("SDET4");
		driver.findElement(By.id("addCandidate_lastName")).sendKeys("Tester4");
		// Entering Candidate Email
		driver.findElement(By.id("addCandidate_email")).sendKeys("candidate4@example.com");
		// Uploading resume
		String resumeFileDir = "C:\\Users\\TuryansuSubhadarshy\\eclipse-workspace\\Cucumber_Live_Projects\\src\\test\\resources\\"
				+ "sample-direct-and-certain-resume.pdf";
		driver.findElement(By.id("addCandidate_resume")).sendKeys(resumeFileDir);
	}
	
	@And("^Click the 'Save' button to save the candidate$")
	public void userClicksOnSaveCandidateButton() {
		driver.findElement(By.id("btnSave")).click();
	}
	
	@Then("^Verify that the candidacy was created$")
	public void verifyNewCandidateWasSaved() {
		// Wait till candidate data is saved and click on 'Back' button
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btnBack")));
		driver.findElement(By.id("btnBack")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[text()='Candidates']")));
		// Entering Candidate Name in search
		driver.findElement(By.id("candidateSearch_candidateName")).sendKeys("Tury4");
		// Selecting Candidate Name from autocomplete dropdown options
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> CandidateNameValues = driver.findElements(By.xpath("//li[contains(@class,'ac')]"));
		for(WebElement cnd:CandidateNameValues) {
			if(cnd.getText().equals("Tury4 SDET4 Tester4")) {
				cnd.click();
				break;
			}
		}
		// Clicking on Search button
		driver.findElement(By.id("btnSrch")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement candidateNameInSearch = driver.findElement(By.xpath("//td[@class='left']//a[contains(text(),'yey')]"));
		Assert.assertTrue(candidateNameInSearch.isDisplayed());
	}
	
	// Activity-3 definitions below:
	
	@Then("^Navigate to the 'PIM' page$")
	public void userNavigatesToPIMMenuOption() {
		// Click on PIM menu option item
		driver.findElement(By.xpath("//b[text()='PIM']")).click();
		driver.findElement(By.xpath("//b[text()='PIM']")).click();
	}
	
	@And("^Click on the 'Add' button to navigate to the 'Add Employee' form$")
	public void userNavigatesToAddEmployeePage() {
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[text()='Add Employee']")));
	}
	
	@And("^Fill out the necessary details on 'Add Employee' page namely \"(.*)\",\"(.*)\",\"(.*)\"$")
	public void fillAllDetailsOnAddEmployeePage(String firstName, String lastName, String userName) {
		// Entering Employee Name
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		// Selecting 'Create Login Details' checkbox
		if (driver.findElement(By.id("chkLogin")).isSelected()) {
			;
		}
		else {
			driver.findElement(By.id("chkLogin")).click();
		}
		WebElement uname = driver.findElement(By.id("user_name"));
		wait.until(ExpectedConditions.visibilityOf(uname));
		uname.sendKeys(userName);
	}
	
	@And("^Click the 'Save' button to save the employee$")
	public void userClicksOnSaveEmployeeButton() {
		driver.findElement(By.id("btnSave")).click();
	}
	
	@Then("^Verify that the \"(.*)\" employee was created$")
	public void verifyNewEnmployeeAdded(String firstName) {
		WebElement newEmployeeHeader = driver.findElement(By.xpath("//h1[contains(text(),'"+firstName+"')]"));
		Assert.assertTrue(newEmployeeHeader.isDisplayed());
	}
	
	// Activity-4 definitions below:
	
	@And("^Fill out the necessary details on 'Add Job Vacancy' page namely \"(.*)\",\"(.*)\",\"(.*)\"$")
	public void fillAllDetailsOnAddJobVacancyPageWithExamples(String jobTitle, String vacancyName, String numPositions) {
		// Entering Job Title via dropdown
		WebElement jobTitleElement = driver.findElement(By.xpath("//select"));
		jobTitleElement.click();
		Select jobTitleDropdown = new Select(jobTitleElement);
		jobTitleDropdown.selectByVisibleText(jobTitle);
		// Entering Vacancy Name
		WebElement vacancyNameElement = driver.findElement(By.xpath("//input[contains(@id,'name')]"));
		vacancyNameElement.sendKeys(vacancyName);
		// Entering Hiring Manager via autocomplete
		driver.findElement(By.xpath("//input[@id='addJobVacancy_hiringManager']")).sendKeys("He");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> HiringManagerValues = driver.findElements(By.xpath("//li[contains(@class,'ac')]"));
		for(WebElement hrmgr:HiringManagerValues) {
			if(hrmgr.getText().equals("Hello Tester")) {
				hrmgr.click();
				break;
			}
		}
		// Entering Number of Positions
		WebElement noOfPositionsElement = driver.findElement(By.id("addJobVacancy_noOfPositions"));
		noOfPositionsElement.sendKeys(numPositions);
		// Entering Description
		WebElement descriptionElement = driver.findElement(By.id("addJobVacancy_description"));
		descriptionElement.sendKeys("This a test case, please apply elsewhere!");
	}
	
}
