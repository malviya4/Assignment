package assignment.testScript;

import assignment.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class demo extends TestBase {
    @Test
    public void demo(){
        driver.findElement(By.cssSelector(element.getProperty("submit"))).click();
      WebElement e= driver.findElement(By.xpath("//*[contains(text(),'Field required')]"));
      String text= e.getText();
      System.out.println(e.getText());
        Assert.assertTrue(text.contains("Field required"));
    }

}
