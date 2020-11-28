package TestNG_Sessions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;

public class Activity2_1 {
	
	WebDriver driver;
	
  @BeforeTest
  public void beforeTest() {
	  //Create a new instance of the Firefox driver
      driver = new FirefoxDriver();
          
      //Open the browser
      driver.get("https://www.training-support.net/selenium/target-practice");
  }

  @Test
  public void testCase1() {
	//This test case will pass
      String title = driver.getTitle();
      System.out.println("Title is: " + title);
      Assert.assertEquals(title, "Target Practice");
  }

  @Test
  public void testCase2() {
	  //This test case will Fail
      WebElement blackButton = driver.findElement(By.cssSelector("button.black"));
      Assert.assertTrue(blackButton.isDisplayed());
      Assert.assertEquals(blackButton.getText(), "black");
  }

  @Test(enabled = false)
  public void testCase3() {
	  //This test will be skipped and not counted
      String subHeading = driver.findElement(By.className("sub")).getText();
      Assert.assertTrue(subHeading.contains("Practice"));
  }
  
  @Test
  public void testCase4() {
	  //This test will be skipped and will be be shown as skipped
      throw new SkipException("Skipping test case"); 
  }
  
  @AfterTest
  public void afterTest() {
	//Close the browser
      driver.close();
  }

}
