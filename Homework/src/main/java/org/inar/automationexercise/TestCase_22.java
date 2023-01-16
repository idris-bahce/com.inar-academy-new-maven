package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

public class TestCase_22 extends BaseTest{
    @BeforeSuite
    public void setUpSuite() {
        // code that is executed before the entire test suite
        String URL = ConfigurationReader.getProperty("url");
        String browser = ConfigurationReader.getProperty("browser");
        String environment = ConfigurationReader.getProperty("environment");
        Driver.getDriver().get(URL);
        System.out.println("::::::Test Information ::::::\n\tURL :" + URL + "\n\tBrowser :" + browser + "\n\tEnvironment :" + environment);
        BrowserUtils.wait(1);
    }
    @Test
    public void verifyHomePageIsVisible(){
        String verifyingWord = Driver.getDriver().findElement(By.xpath("//li/a[@style='color: orange;']")).getText();
        Assert.assertEquals(verifyingWord,"Home");
    }
    @Test
    public void verifyRECOMMENDEDITEMSAreVisible(){
        JavascriptExecutor j = (JavascriptExecutor)Driver.getDriver();
        j.executeScript("window.scrollBy(0,500)");
        BrowserUtils.waitForVisibility(By.xpath("(//h2[normalize-space()='recommended items'])[1]"),5);
        String verifyingString = Driver.getDriver().findElement(By.xpath("(//h2[normalize-space()='recommended items'])[1]")).getText();
        Assert.assertEquals(verifyingString,"RECOMMENDED ITEMS");
    }
    @Test
    public void clickOnViewCartButton(){
        Driver.getDriver().findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[73]")).click();
        Driver.getDriver().findElement(By.xpath("(//u[normalize-space()='View Cart'])[1]")).click();
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("a[href='/product_details/5']")).getText();
        Assert.assertEquals(verifyingString,"Winter Top");
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }
}
