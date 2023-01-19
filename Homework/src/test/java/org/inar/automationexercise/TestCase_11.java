package org.inar.automationexercise;

import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase_11 extends BaseTest{
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
    public void verifyHomePageIsVisible() {
        String verifyingWord = Driver.getDriver().findElement(By.xpath("//li/a[@style='color: orange;']")).getText();
        Assert.assertEquals(verifyingWord, "Home");
    }

    @Test
    public void verifyTextSubscription(){
        pages.getHomePage().navigateTo(" Cart");
        JavascriptExecutor j = (JavascriptExecutor) Driver.getDriver();
        j.executeScript("window.scrollBy(0,800)");
        BrowserUtils.waitForVisibility(By.cssSelector("div[class='single-widget'] h2"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='single-widget'] h2")).getText();
        Assert.assertEquals(verifyingString,"SUBSCRIPTION");
    }
    @Test
    public void verifySuccessMessageYouHaveBeenSuccessfullySubscribedIsVisible(){
        Driver.getDriver().findElement(By.id("susbscribe_email")).sendKeys("bahce@bahce");
        Driver.getDriver().findElement(By.cssSelector("#subscribe")).click();
        BrowserUtils.waitForVisibility(By.id("success-subscribe"),1);
        String verifyingString = Driver.getDriver().findElement(By.id("success-subscribe")).getText();
        Assert.assertEquals(verifyingString,"You have been successfully subscribed!");
    }

    @AfterSuite
    public void teardown(){
        Driver.getDriver().quit();
    }

}
