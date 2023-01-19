package org.inar.automationexercise;

import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase_05 extends BaseTest{
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
    public void verifyNewUserSignupIsVisible(){
        pages.getHomePage().clickToSignupAndLoginButton();
        BrowserUtils.waitForVisibility(By.cssSelector("div[class='signup-form'] h2"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='signup-form'] h2")).getText();
        Assert.assertEquals(verifyingString,"New User Signup!");
    }
    @Test
    public void verifyErrorEmailAddressAlreadyExistIsVisible(){
        pages.getLoginPage().sendEmailToSignup("bahce@bahce");
        pages.getLoginPage().sendNameToSignup("bahce");
        pages.getLoginPage().clickToSignupButton();
        BrowserUtils.waitForVisibility(By.xpath("//p[@style='color: red;']"),2);
        String verifyingString = Driver.getDriver().findElement(By.xpath("//p[@style='color: red;']")).getText();
        Assert.assertEquals(verifyingString,"Email Address already exist!");
    }
    @AfterSuite
    public void teardown(){
        Driver.getDriver().quit();
    }
}
