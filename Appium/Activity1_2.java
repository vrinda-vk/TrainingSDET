package Appium_Sessions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity1_2 {
    @Test
    public void calc() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "fc8d9175");
        caps.setCapability("deviceName", "OnePlus 8T");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.oneplus.calculator");
        caps.setCapability("appActivity", "com.oneplus.calculator.Calculator");

        // Instantiate Appium Driver
        AppiumDriver<MobileElement> driver = null;
        // Initialize driver
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
    	System.out.println("Calculator is open");
        driver.quit();
    
    }

}
