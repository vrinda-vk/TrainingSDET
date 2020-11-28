package TestNG_Sessions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class ScreenshotDemo {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public void beforeTest() {
    	driver = new FirefoxDriver();
    	wait = new WebDriverWait(driver, 10);
        driver.get("http://google.com");
    }

    //Test case 1
    @Test
    public void seleniumSnapshotTest() throws Exception {
        driver.findElement(By.name("q")).sendKeys("Selenium", Keys.RETURN);

        //Wait for the results to appear
        wait.until(ExpectedConditions.titleContains("Selenium"));
        
        //Wait till results show
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("result-stats")));

        boolean linkStatus = driver.findElement(By.xpath("//a[contains(@href, 'www.selenium.dev')]")).isDisplayed();
        //Assertion
        Assert.assertEquals(linkStatus, true);
        
        //Call the method to take a screenshot
        //and add it to the report
        takeScreenshot();
    }

    @AfterClass
    public void endOfTest() {
        System.out.println("end of test");
        driver.quit();
    }

    public static void takeScreenshot() throws Exception {
    	String timeStamp;
    	File screenShotName;

    	//Take screenshot
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

    	//The below method will save the screen shot in with name "yyyyMMdd_HHmmss.jpg"
    	timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

    	//This specifies the location the screenshot will be saved
    	screenShotName = new File("src/test/resources/"+timeStamp+".jpg");

    	//This will copy the screenshot to the file created
    	FileUtils.copyFile(scrFile, screenShotName);

        //Set filepath for image
        String filePath = "../"+screenShotName.toString();
        //Set image HTML in the report
        String path = "<img src='"+ filePath +"'/>";
        //Show image in report
        Reporter.log(path);
    }
}
