package Appium_Project_Activities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity1_2 {
	
	AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "OnePlus 8T");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "com.google.android.keep");
		caps.setCapability("appActivity", ".activities.BrowseActivity");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 10);
    }
    
    @Test
    public void addNote() {
    	driver.findElementByAccessibilityId("New text note").click();
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.google.android.keep:id/new_note_button")));
    	// find the Title of task and note
    	MobileElement newTitle = driver.findElementById("com.google.android.keep:id/editable_title");
    	newTitle.sendKeys("Example Note Title");
    	MobileElement newNote =  driver.findElementById("com.google.android.keep:id/edit_note_text");
	    newNote.sendKeys("Example Note Description");
	    driver.findElementByAccessibilityId("Open navigation drawer").click();
	    // Wait for Note to Display
	    wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.keep:id/browse_note_interior_content")));
	    //Assertion
	    MobileElement noteDisplay = driver.findElementById("com.google.android.keep:id/browse_note_interior_content");
	    Assert.assertTrue(noteDisplay.isDisplayed());
	    System.out.println("Note added successfully");
    }
    
    @AfterClass
    public void tearDown() {
    	driver.quit();
    }
	
}
