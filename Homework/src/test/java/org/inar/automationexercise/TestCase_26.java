package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;

public class TestCase_26 extends BaseTest{
    private static JavascriptExecutor j = (JavascriptExecutor) Driver.getDriver();
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
    public void verifySUBSCRIPTIONIsVisible(){
        j.executeScript("window.scrollBy(0,8000)");
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='single-widget'] h2")).getText();
        Assert.assertEquals(verifyingString,"SUBSCRIPTION");
    }
    @Test
    public void  verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen(){
        j.executeScript("window.scrollBy(0,-8000)");
        BrowserUtils.wait(1);
        String verifyingString = Driver.getDriver().findElement(By.xpath("//h2[contains(text(),'Full-Fledged practice website for Automation Engin')]")).getText();
        Assert.assertEquals(verifyingString,"Full-Fledged practice website for Automation Engineers");
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }
}
