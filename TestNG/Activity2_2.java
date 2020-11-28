package TestNG_Sessions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Activity2_2 {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void beforeClass() {
		//Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
		
		//Create a new instance of the WebDriverWait
		wait = new WebDriverWait(driver, 10);
		
		//Open the browser
		driver.get("https://www.training-support.net/selenium/login-form");
	  }
	
	@Test
	public void loginTest() throws InterruptedException {
		//Enter credentials
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("//button[text() = 'Log in']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("action-confirmation")));
				
		String message = driver.findElement(By.id("action-confirmation")).getText();
		Assert.assertEquals(message, "Welcome Back, admin");
	}

	@AfterClass
	public void afterClass() {
	  //Close the browser
	  driver.close();
	}

}
