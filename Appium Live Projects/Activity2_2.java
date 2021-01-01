package Appium_Project_Activities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Activity2_2 {
	
	AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait;

    @BeforeTest
    public void setup() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "OnePlus 8T");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "org.mozilla.firefox");
		caps.setCapability("appActivity", "org.mozilla.fenix.HomeActivity");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 10);
    }
	
    @Test(priority=0)
    public void ValidLogInTest() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.training-support.net/selenium");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View")));
        //Scroll to the Login Form
        driver.findElement(MobileBy.
        		AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Login Form\"))")).click();
		//Enter username
        MobileElement userName = driver.findElement(By.xpath("//android.view.View/android.view.View[3]/"
        		+ "android.view.View/android.view.View/android.widget.EditText[1]"));
        userName.click();
        userName.sendKeys("admin");
        driver.findElement(MobileBy.
        		AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Password\"))")).click();
         //Enter password
	      driver.findElementByXPath("//android.view.View/android.view.View[3]/android.view.View/android.view.View/"
	      		+ "android.widget.EditText[2]").click();
		  driver.findElementByXPath("//android.view.View/android.view.View[3]/android.view.View/android.view.View/"
		  		+ "android.widget.EditText[2]").sendKeys("password");
		  driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();
          String loginSuccess = driver.findElementByXPath("//android.view.View[@resource-id='action-confirmation']").getText();
		  Assert.assertEquals(loginSuccess, "Welcome Back, admin");
		  System.out.println("Login Successful");
    }	  
	
    @Test(priority=1)
    public void InvalidLogInTest() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.training-support.net/selenium");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View")));
        //Scroll to the Login Form
        driver.findElement(MobileBy.
        		AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Login Form\"))")).click();
		//Enter username
        MobileElement userName = driver.findElement(By.xpath("//android.view.View/android.view.View[3]/"
        		+ "android.view.View/android.view.View/android.widget.EditText[1]"));
        userName.click();
        userName.sendKeys("adadmin");
        driver.findElement(MobileBy.
        		AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Password\"))")).click();
         //Enter password
	      driver.findElementByXPath("//android.view.View/android.view.View[3]/android.view.View/android.view.View/"
	      		+ "android.widget.EditText[2]").click();
		  driver.findElementByXPath("//android.view.View/android.view.View[3]/android.view.View/android.view.View/"
		  		+ "android.widget.EditText[2]").sendKeys("papassassword");
		  driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();
		  String loginFailure = driver.findElementByXPath("//android.view.View[@resource-id='action-confirmation']").getText();
		  Assert.assertEquals(loginFailure, "Invalid Credentials");
		  System.out.println("Login Failed");
    }    
    
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
	
}
