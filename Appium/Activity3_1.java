package Appium_Sessions;

import java.net.MalformedURLException;
import java.net.URL;

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

public class Activity3_1 {
    AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "OnePlus 8T");
        caps.setCapability("platformName", "Android");
        caps.setCapability("noReset", true);
        // Use your own device's messaging app
        caps.setCapability("appPackage", "com.google.android.apps.messaging");
        caps.setCapability("appActivity", ".ui.ConversationListActivity");

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void smsTest() {
        // Locate the button to write a new message and click it
        driver.
        findElement
        (MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button_v2\")"))
        .click();

        // Enter the number to send message to
        driver.
        findElement(MobileBy.id("com.google.android.apps.messaging:id/recipient_text_view"))
        .sendKeys("8763788497");

        // Focus on the message text box
        driver.findElement(MobileBy.xpath("//android.widget.TextView[contains(@text ,  \"88497\")]")).click();

        // Type in a message
        driver.findElement(MobileBy.xpath("//android.widget.EditText[contains(@text ,  \"Text\")]"))
        .sendKeys("Hello from Appium");

        // Send the message
        String sendButtonLocator = "resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")";
        driver.findElement(MobileBy.AndroidUIAutomator(sendButtonLocator)).click();

        // Wait for message to show
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.google.android.apps.messaging:id/message_text")));

        // Assertion
        String messageLocator = "resourceId(\"com.google.android.apps.messaging:id/message_text\")";
        String sentMessageText = driver.findElement(MobileBy.AndroidUIAutomator(messageLocator)).getText();
        Assert.assertEquals(sentMessageText, "Hello from Appium");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
