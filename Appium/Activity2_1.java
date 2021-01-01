package Appium_Sessions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity2_1 {
	
	AppiumDriver<MobileElement> driver = null;
	WebDriverWait wait;
	
	@BeforeTest
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "OnePlus 8T");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "org.mozilla.firefox");
		caps.setCapability("appActivity", "org.mozilla.fenix.HomeActivity");
		
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer,caps);
	}
	
	@Test
	public void browserTest() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.training-support.net/");
		MobileElement pageTitleElement = driver.
				findElement(MobileBy.xpath("//android.view.View[@content-desc=\"Training Support\"]/android.view.View"));
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(pageTitleElement));
		String pageTitle = pageTitleElement.getText();
		System.out.println("Title of the page is: " + pageTitle);
		driver.findElementByAccessibilityId(pageTitle).click();
		MobileElement newPageTitleElement = driver.
				findElement(MobileBy.xpath("//android.view.View[@content-desc=\"About Us\"]/android.view.View"));
		wait.until(ExpectedConditions.elementToBeClickable(newPageTitleElement));
		String newPageTitle = newPageTitleElement.getText();
		System.out.println("Title of the new page is: " + newPageTitle);
		
		Assert.assertEquals(pageTitle, "Training Support");
		Assert.assertEquals(newPageTitle, "About Us");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
