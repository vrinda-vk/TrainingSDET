package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class JobBoardSteps {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void setup() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,10);
	}
	
	@After
	public void closeBrowser() {
		driver.close();
	}
	
	// Activity-1 definitions below:
	
	@Given("^User is on Alchemy Jobs Admin webpage$")
	public void userOnAlchemyJobsAdminPage() {
		// Open the webpage
		driver.get("https://alchemy.hguy.co/jobs/wp-admin");
		// Enter username
		driver.findElement(By.xpath("//p//input[@name = 'log']")).sendKeys("root");
		// Enter password
		driver.findElement(By.xpath("//div//input[@name = 'pwd']")).sendKeys("pa$$w0rd");
		// Click on LogIn button
		driver.findElement(By.cssSelector("#wp-submit")).click();
		// Instantiate the wait
		wait = new WebDriverWait(driver,10);
		// Wait for new page to load
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#wp-submit")));
	}
	
	@And("^Locate the left hand menu and click the menu item that says 'Users'$")
	public void userClicksOnUsermenuItem() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#menu-users > a > div.wp-menu-name")));
		driver.findElement(By.cssSelector("#menu-users > a > div.wp-menu-name")).click();
	}
	
	@And("^Locate the 'Add New' button and click it$")
	public void userClicksOnAddNewButton() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#menu-users > ul > li:nth-child(3) > a")));
		driver.findElement(By.cssSelector("#menu-users > ul > li:nth-child(3) > a")).click();
	}
	
	@And("^Fill in the necessary details$")
	public void userFillsDetailsForNewUser() {
		// Enter all fields
		driver.findElement(By.id("user_login")).sendKeys("ladidah4");
		driver.findElement(By.id("email")).sendKeys("ladidah4@example.com");
		driver.findElement(By.id("first_name")).sendKeys("Ladi4");
		driver.findElement(By.id("last_name")).sendKeys("Dah4");
		// Click on password button
		driver.findElement(By.xpath("//button[contains(@class,'generate')]")).click();
		driver.findElement(By.xpath("//input[@id='pass1']")).clear();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='pass-strength-result']")));
		driver.findElement(By.xpath("//input[@id='pass1']")).sendKeys("ladidah@ladidah");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='pass-strength-result']")));
	}
	
	@And("^Click the 'Add New User' button$")
	public void userClicksOnAddNewUserButton() {
		driver.findElement(By.xpath("//p[@class='submit']//input")).click();
	}
	
	@Then("^Verify that the user was created$")
	public void verifyNewUserAdded() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("wpbody-content")));
		// Search for new User by the username
		driver.findElement(By.xpath("//input[@id='user-search-input']")).sendKeys("ladidah3");
		driver.findElement(By.id("search-submit")).click();
		Assert.assertTrue(driver.findElement(By.xpath
				("//table//td[@class='email column-email']//a[contains(text(),\"ladidah4@example.com\")]")).isDisplayed());
	}
	
	// Activity-2 definitions below:
	
	@Given("^User is on Alchemy Jobs site$")
	public void userOnAlchemyPage() {
		// Open the webpage
		driver.get("https://alchemy.hguy.co/jobs/");
	}
	
	@And("^Click on 'Jobs' menu button to navigate to Jobs page$")
	public void userClicksOnJobsMenuButton() {
		driver.findElement(By.linkText("Jobs")).click();
		// Wait for new page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("h1")));
	}
		
	@And("^Type in keywords into search input field to search for jobs$")
	public void userTypesKeywordsForJobSearch() {
		WebElement SearchInput = driver.findElement(By.id("search_keywords"));
		SearchInput.sendKeys("test");
	}
	
	@And("^Find the filter using XPath and filter job type to show only 'Full Time' jobs$")
	public void putFilterOnJobTYpeForSeach() {
		// Define all the checkboxes
		WebElement Chkbox_Freelance = driver.findElement(By.cssSelector("#job_type_freelance"));
		WebElement Chkbox_FullTime = driver.findElement(By.cssSelector("#job_type_full-time"));
		WebElement Chkbox_Internship = driver.findElement(By.cssSelector("#job_type_internship"));
		WebElement Chkbox_PartTime = driver.findElement(By.cssSelector("#job_type_part-time"));
		WebElement Chkbox_Temporary = driver.findElement(By.cssSelector("#job_type_temporary"));
		// Filtering for only Full Time jobs
		if (Chkbox_Freelance.isSelected()) {
			Chkbox_Freelance.click();
		}
		if (Chkbox_Internship.isSelected()) {
			Chkbox_Internship.click();
		}
		if (Chkbox_PartTime.isSelected()) {
			Chkbox_PartTime.click();
		}
		if (Chkbox_Temporary.isSelected()) {
			Chkbox_Temporary.click();
		}
		if (Chkbox_FullTime.isSelected()) {
			;
		}
		else {
			Chkbox_FullTime.click();
		}
	}
	
	@And("^Find a job listing using XPath and it to see job details$")
	public void userFindsJobDetails() {
		// Clicking the "Search Jobs" button
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	    // Waiting for search results to load
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'showing_jobs')]"))));
	}
	
	@And("^Find the title of the job listing using XPath and print it to the console$")
	public void printTitleOfJobFound() {
		// Print title of the 1st job which is not filled and still available
		String JobTitle = driver.findElement
				(By.xpath("//li[contains(@class,'job') and not(contains(@class,'filled'))][1]//a//div//h3")).getText();
		System.out.println("Ttile of the job is : "+JobTitle);
	}
	
	@Then("^Find and Click on the 'Apply for job' button$")
	public void applyForJobButtonClick() {
		// Click on the 1st open job
		driver.findElement(By.xpath("//li[contains(@class,'job') and not(contains(@class,'filled'))][1]//a")).click();
		// Wait for new page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='button']")));
		// Click on "Apply for job" button
		driver.findElement(By.xpath("//input[@type='button']")).click();
		// Print the contact details to apply for the job
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class = 'job_application_email']")));
		String emailId = driver.findElement(By.xpath("//a[@class = 'job_application_email']")).getText();
	    System.out.println("To apply for this job email your details to : " + emailId);
	}
	
	// Activity-3 & 4 definitions below:
	
	@And("^Go to 'Post a Job' page$")
	public void goToPostAJobPage() {
		// Clicking on "Post a Job" menu button
        driver.findElement(By.xpath("//a[@href='https://alchemy.hguy.co/jobs/post-a-job/']")).click();
	}
	
	@And("^User is logged into Alchemy Jobs Posting site$")
	public void verifyUserIsLoggedInPostAJobPageAndLogInIfNot() {
		wait = new WebDriverWait(driver,10);
        // Verifying if user is not logged in, then perform log in
        if (driver.findElement(By.xpath("//a[@class='button']")).getText().toString() == "Sign in") {
        	driver.findElement(By.xpath("//a[@class='button']")).click();
        	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='log']"))));
        	driver.findElement(By.xpath("//input[@name='log']")).sendKeys("root");
        	driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("pa$$w0rd");
        	driver.findElement(By.cssSelector("#wp-submit")).click();
        	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("job_title"))));
        }
        // If user is already logged in, no action needed
        else {
        	;
        }
	}
	
	@And("^Fill details on 'Post a Job' page from feature file namely \"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\"$")
	public void fillAllDetailsOnPostAJobPage(String email,String jobTitle,String description,String appURL,String companyName) {
		// Entering email
        WebElement  yourEmail= driver.findElement(By.xpath("//input[@id = 'create_account_email']"));
        yourEmail.sendKeys(email);
        // Entering job title
	    WebElement jobTitleElement = driver.findElement(By.xpath("//input[@id = 'job_title']"));
	    jobTitleElement.sendKeys(jobTitle);
	    // Switching to iframe for entering Description of job
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='job_description_ifr']")));
        // Entering description and switching back to main page
        WebElement  descriptionPage= driver.findElement(By.xpath("//body[@id='tinymce']"));
        descriptionPage.sendKeys(description);
        driver.switchTo().defaultContent();
        // Entering application email or application URL
        WebElement  applicationEmail= driver.findElement(By.xpath("//input[@id = 'application']"));
        applicationEmail.sendKeys(appURL);
        // Entering company name
        WebElement  companyNameElement = driver.findElement(By.xpath("//input[@id = 'company_name']"));
        companyNameElement.sendKeys(companyName);
	}
	
	@And("^Submit the job listing$")
	public void submitPostAJob() {
		// Clicking on "Preview" button
        driver.findElement(By.xpath("//input[@value='Preview']")).click();
        // Clicking on "Submit Listing" button
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='job_preview_submit_button']")));
        driver.findElement(By.xpath("//input[@id='job_preview_submit_button']")).click();
	}
	
	@Then("^Confirm \"(.*)\" job was posted$")
	public void confirmPostedJobIsListed(String jobTitle) {
		// Verifying the listed job by clicking on "click here" link
        driver.findElement(By.linkText("click here")).click();
        // Assertion for verifying if job is listed
        Assert.assertTrue(driver.getTitle().toString().contains(jobTitle.toString()));
        System.out.println("\n"+ jobTitle + ": Job has been posted successfully");
	}
	
}
