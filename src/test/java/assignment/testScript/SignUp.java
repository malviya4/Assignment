package assignment.testScript;

import assignment.base.TestBase;
import assignment.utilities.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SignUp extends TestBase {
    @Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
    public void signUp(String fullName, String emailId, String countryCode, String mobileNum, String message) throws InterruptedException {
       // driver.manage().window().fullscreen();
        WebElement name = driver.findElement(By.cssSelector(element.getProperty("fullName")));
        name.clear();
        name.sendKeys(fullName);
        WebElement emailField = driver.findElement(By.cssSelector(element.getProperty("emailId")));
        emailField.clear();
        emailField.sendKeys(emailId);
        WebElement dropdown = driver.findElement(By.cssSelector(element.getProperty("countryCode_CSS")));
        Select select = new Select(dropdown);
        select.selectByValue(countryCode);
        WebElement number = driver.findElement(By.cssSelector(element.getProperty("mobileNum")));
        number.clear();
        number.sendKeys(mobileNum);
        WebElement messageBox = driver.findElement(By.cssSelector(element.getProperty("message")));
        messageBox.clear();
        messageBox.sendKeys(message);
        driver.findElement(By.cssSelector(element.getProperty("submit"))).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)", "");
        driver.findElement(By.xpath("//div[@class='pl-3 pt-3']/child::div[contains(text(),'Message sent successfully!')]"));
        driver.navigate().refresh();

    }
}
