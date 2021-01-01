package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CRMSteps {
	
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
	
	@Given("^User is on Alchemy CRM site and logged in$")
	public void userIsOnAlchemyCRMSiteAndLoggedIn() {
		driver.get("https://alchemy.hguy.co/crm/");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("user_name")));
		// Entering credentials
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		// Clicking on log in button
		driver.findElement(By.id("bigbutton")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='toolbar']")));
	}
	
	@Then("^Count the number of Dashlets on the homepage$")
	public void countDashletsOnCRMHomepage() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3")));
		// Passing all h3 header elements into a list to get the count of dashlets
		List<WebElement> dashletCountList = driver.findElements(By.xpath("//h3"));
		System.out.println("Number of dashlets on the homepage : " + dashletCountList.size());
	}
	
	@And("^Print the number and title of each Dashlet$")
	public void printAllDashletHeaders() {
		// Printing dashlet title from the span element within each h3 tag
		List<WebElement> dashlets = driver.findElements(By.xpath("//h3//span//following-sibling::span"));
		int i = 0;
		for (WebElement d:dashlets) {
			i+=1;
			System.out.println("Header of dashlet #"+ i +" is: "+ d.getText());
		}
	}
	
	// Activity-2 definitions below:
	
	@Then("^Navigate to Sales -> Leads -> Create Lead$")
	public void userNavigatesToCreateLeadPage() {
		// Hovering on 'Sales' menu button
		WebElement salesElement = driver.findElement(By.xpath("//a[text()='Sales']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(salesElement).perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
				(By.xpath("//a[@id='moduleTab_9_Leads' and contains(@href,'Sales')]")));
		// Clicking on 'Leads' sub-menu button
		driver.findElement(By.xpath("//a[@id='moduleTab_9_Leads' and contains(@href,'Sales')]")).click();
		// Wait for new page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Leads')]")));
		// Clicking on 'Create Lead' from the left pane
		driver.findElement(By.xpath("//div[@class='actionmenulink' and text() = 'Create Lead']")).click();
		// Wait for Create Lead page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'CREATE')]")));
	}
	
	@And("^Fill in the necessary details to create lead accounts using first "
			+ "name as \"(.*)\" , last name as \"(.*)\" , lead status as \"(.*)\"$")
	public void fillAllDetailsOnCreateLeadPage(String fname,String lname,String status) {
		// Entering details
		driver.findElement(By.id("first_name")).sendKeys(fname);
		driver.findElement(By.id("last_name")).sendKeys(lname);
		WebElement leadStatus = driver.findElement(By.id("status"));
		leadStatus.click();
		Select leadStatusDropdown = new Select(leadStatus);
		leadStatusDropdown.selectByVisibleText(status);
	}
	
	@And("^Click Save to finish$")
	public void clickSaveToSaveLeadCreated() {
		driver.findElement(By.id("SAVE")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Then("^Navigate to the View Leads page to see results for first name as \"(.*)\" , last name as \"(.*)\"$")
	public void usernavigatesToViewLeadsPage(String fname,String lname) {
		// Clicking on 'View Leads' from the left pane
		driver.findElement(By.xpath("//div[@class='actionmenulink' and text() = 'View Leads']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.
				xpath("//table[@class='list view table-responsive']/tbody/tr[1]/td[3]/b/a")));
		String nameFromViewLeadsPage = driver.findElement(By.
				xpath("//table[@class='list view table-responsive']/tbody/tr[1]/td[3]/b/a")).getText();
		String fullNameFromData = fname+" "+lname;
		System.out.println("Expected: "+fullNameFromData);
		System.out.println("Actual: "+nameFromViewLeadsPage);
		Boolean assertBool = nameFromViewLeadsPage.equals(fullNameFromData);
		Assert.assertTrue(assertBool);
		System.out.println("Lead with name "+fullNameFromData+" was created successfully");
	}
	
	// Activity-3 definitions below:
	
	@Then("^Navigate to Activities -> Meetings -> Schedule a Meeting$")
	public void userNavigatesToScehduleMeetingPage() {
		// Hovering on 'Activities' menu button
		WebElement activitiesElement = driver.findElement(By.xpath("//a[text()='Activities']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(activitiesElement).perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
				(By.xpath("//a[@id='moduleTab_9_Meetings' and contains(@href,'Activities')]")));
		// Clicking on 'Meetings' sub-menu button
		driver.findElement(By.xpath("//a[@id='moduleTab_9_Meetings' and contains(@href,'Activities')]")).click();
		// Wait for new page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Meetings')]")));
		// Clicking on 'Schedule Meeting' from the left pane
		driver.findElement(By.xpath("//div[@class='actionmenulink' and text() = 'Schedule Meeting']")).click();
		// Wait for Schedule Meeting page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'CREATE')]")));
	}
	
	@And("^Enter the details of the meeting$")
	public void userEntersMeetingDetails() {
		// Entering meeting Subject
		driver.findElement(By.id("name")).sendKeys("This is test generated meeting #031");
		// Entering start date & time
		driver.findElement(By.id("date_start_date")).clear();
		driver.findElement(By.id("date_start_date")).sendKeys("12/31/2020");
		WebElement startHour = driver.findElement(By.id("date_start_hours"));
		startHour.click();
		Select startHourSelect = new Select(startHour);
		startHourSelect.selectByVisibleText("16");
		WebElement startMins = driver.findElement(By.id("date_start_minutes"));
		startMins.click();
		Select startMinsSelect = new Select(startMins);
		startMinsSelect.selectByVisibleText("00");
		// Entering end date & time
		driver.findElement(By.id("date_end_date")).clear();
		driver.findElement(By.id("date_end_date")).sendKeys("12/31/2020");
		WebElement endHour = driver.findElement(By.id("date_end_hours"));
		endHour.click();
		Select endHourSelect = new Select(endHour);
		endHourSelect.selectByVisibleText("16");
		WebElement endMins = driver.findElement(By.id("date_end_minutes"));
		endMins.click();
		Select endMinsSelect = new Select(endMins);
		endMinsSelect.selectByVisibleText("15");
		// Entering meeting Description
		driver.findElement(By.id("description")).sendKeys("This is not a real meeting, no need to join ;)");
	}
	
	@And("^Search for below users and add them to the meeting$")
	public void searchForUsersToAddToMeeting(DataTable dt) throws InterruptedException {
		List<List<String>> list = dt.asLists(String.class);
		for(int i=1; i<list.size(); i++) {
			// Entering first name in search
			driver.findElement(By.id("search_first_name")).clear();
			driver.findElement(By.id("search_first_name")).sendKeys(list.get(i).get(0));
			// Entering last name in search
			driver.findElement(By.id("search_last_name")).clear();
			driver.findElement(By.id("search_last_name")).sendKeys(list.get(i).get(1));
			// Clicking on 'Search' button
			driver.findElement(By.id("invitees_search")).click();
			// Add User
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("invitees_add_1")));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("invitees_add_1")));
			driver.findElement(By.id("invitees_add_1")).click();
		}
	}
	
	@And("^Click Save to save the meeting$")
	public void saveTheMeeting() {
		driver.findElement(By.id("SAVE_HEADER")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@Then("^Navigate to View Meetings page and confirm creation of the meeting$")
	public void verifyMeetingCreated() {
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//div[@class='actionmenulink' and text() = 'View Meetings']")));
		driver.findElement(By.xpath("//div[@class='actionmenulink' and text() = 'View Meetings']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.
				xpath("//table[@class='list view table-responsive']/tbody/tr[1]/td[4]/b/a")));
		String meetingSubject = driver.findElement(By.
				xpath("//table[@class='list view table-responsive']/tbody/tr[1]/td[4]/b/a")).getText();
		// Assertion string to be changed below as per the meeting subject defined in earlier step
		Assert.assertTrue(meetingSubject.equalsIgnoreCase("This is test generated meeting #031"));
		System.out.println("A meeting with the subject '"+meetingSubject+"' was succesfully created");
	}
	
	// Activity-4 definitions below:
	
	@Then("^Navigate to All -> Products-> Create Product$")
	public void userNavigatesToCreateProductPage() {
		// Hovering on 'All' menu button
		WebElement allElement = driver.findElement(By.xpath("//a[text()='All']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(allElement).perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
				(By.xpath("//a[text()='Products' and contains(@href,'Products')]")));
		// Clicking on 'Meetings' sub-menu button
		driver.findElement(By.xpath("//a[text()='Products' and contains(@href,'Products')]")).click();
		// Wait for new page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'Products')]")));
		// Clicking on 'Create Product' from the left pane
		driver.findElement(By.xpath("//div[@class='actionmenulink' and text() = 'Create Product']")).click();
		// Wait for Schedule Meeting page to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[contains(text(),'CREATE')]")));
	}
	
	@And("^Enter the details of the product like \"(.*)\" and \"(.*)\"$")
	public void fillAllDetailsOnCreateProductPage(String productName,String price) {
		// Entering product name
		driver.findElement(By.id("name")).sendKeys(productName);
		// Entering product price
		driver.findElement(By.id("price")).sendKeys(price);
		// Entering product description
		driver.findElement(By.id("description")).sendKeys("This is a test product created by Tury");
	}
	
	@And("^Click Save to save the product$")
	public void userClicksOnSaveToSaveProduct() {
		driver.findElement(By.id("SAVE")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@Then("^Go to the View Products page to see all products listed$")
	public void viewProductsCreated() {
		// Clicking on 'View Products' from the left pane
		driver.findElement(By.xpath("//div[@class='actionmenulink' and text() = 'View Products']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.
				xpath("//table[@class='list view table-responsive']/tbody/tr[1]/td[3]/b/a")));
		String productNameSaved = driver.findElement(By.
				xpath("//table[@class='list view table-responsive']/tbody/tr[1]/td[3]/b/a")).getText();
		String productPriceSaved = driver.findElement(By.
				xpath("//table[@class='list view table-responsive']/tbody/tr[1]/td[6]")).getText();
		System.out.println("A product with the name '"+productNameSaved+
				"' and price '"+productPriceSaved+"' was succesfully created");
	}
	
}
