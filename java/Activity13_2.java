import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Activity13_2 {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
        //Open browser
        driver.get("https://www.training-support.net/selenium/lazy-loading");
        
        //Find the second card
        WebElement secondCard = driver.findElement(By.xpath("//div[@class='spaced'][2]//div[@class='image']/img"));
        
        //Before Scrolling
        System.out.println("Image src: " + secondCard.getAttribute("src"));
        
        //Scroll the card into view
        js.executeScript("window.scrollBy(0,1000)");
        
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(secondCard, "src", "loading")));
        
        //After scrolling
        System.out.println("Image src: " + secondCard.getAttribute("src"));
        
        //Close browser
        driver.close();

    }
}
