package Appium_Sessions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

public class Activity2_2 {
	
    WebDriverWait wait;
    // Instantiate Appium Driver
    AppiumDriver<MobileElement> driver = null;
 
    @BeforeTest
    public void setup() throws MalformedURLException {
 
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "OnePlus 8T");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.oneplus.calculator");
        caps.setCapability("appActivity", "com.oneplus.calculator.Calculator");
        caps.setCapability("noReset", true);
        // Instantiate Appium Driver
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
        
    }
	
	@Test
    public void mult() {
        
		driver.findElementById("digit_5").click();
		driver.findElementById("digit_8").click();
		driver.findElementById("op_mul").click();
		driver.findElementById("digit_2").click();
		driver.findElementById("eq").click();
		String result = driver.findElementById("result").getText();
		Assert.assertEquals("116", result);
        
	}
	
	@AfterTest
    public void tearDown() {
        driver.quit();
    }
	
}
