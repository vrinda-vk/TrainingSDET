package Appium_Project_Activities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity1_1 {
	
	AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "OnePlus 8T");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "com.google.android.apps.tasks");
		caps.setCapability("appActivity", ".ui.TaskListsActivity");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 10);
    }
    
    @Test
    public void addtask1() {
    	driver.findElementByAccessibilityId("Create new task").click();
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.google.android.apps.tasks:id/add_task_title")));
    	// Find the new task fields
        MobileElement newTask = driver.findElementByXPath("//android.widget.EditText[@text='New task']");
        // Enter the text in the fields
        newTask.sendKeys("Complete Activity with Google Tasks");
        // Save the task
        driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
     }
    
    @Test
    public void addtask2() {
    	driver.findElementByAccessibilityId("Create new task").click();
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.google.android.apps.tasks:id/add_task_title")));
    	// Find the new task fields
        MobileElement newTask = driver.findElementByXPath("//android.widget.EditText[@text='New task']");
        // Enter the text in the fields
        newTask.sendKeys("Complete Activity with Google Keep");
        // Save the task
        driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
     }
    
    @Test
    public void addtask3() {
    	driver.findElementByAccessibilityId("Create new task").click();
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.google.android.apps.tasks:id/add_task_title")));
    	// Find the new task fields
        MobileElement newTask = driver.findElementByXPath("//android.widget.EditText[@text='New task']");
        // Enter the text in the fields
        newTask.sendKeys("Complete the second Activity Google Keep");
        // Save the task
        driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
     }
	
    @Test
    public void tasksVerify() {
    	List<MobileElement> tasks = driver.findElements(By.xpath("//android.widget.FrameLayout/child::*[contains(@content-desc,'complete')]"));
    	for (MobileElement i:tasks) {
    		System.out.println(i.getText());
    	}
    	Assert.assertEquals(tasks.size(), 3);
    	System.out.println("All 3 tasks were saved successfully");
    }

    @AfterClass
    public void tearDown() {
    	driver.quit();
    }
    
}
