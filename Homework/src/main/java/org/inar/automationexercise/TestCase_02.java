package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

public class TestCase_02 extends BaseTest{
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
    public void goToLoginSingUpPage(){
        HomePage homePage = new HomePage();
        homePage.clickToSignupAndLoginButton();
        BrowserUtils.waitForVisibility(By.cssSelector("div[class='login-form'] h2"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='login-form'] h2")).getText();
        Assert.assertEquals(verifyingString,"Login to your account");
    }
    @Test
    public void verifyThatLoggedInAsUsernameIsVisible(){
        LoginPage loginPage = new LoginPage();
        loginPage.sendEmail("bahce@bahce");
        loginPage.sendPassword("bahce");
        loginPage.login();
        BrowserUtils.waitForVisibility(By.cssSelector("li:nth-child(10) a:nth-child(1)"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("li:nth-child(10) a:nth-child(1)")).getText();
        Assert.assertEquals(verifyingString,"Logged in as bahce");
    }
    @Test
    public void verifyThatAccountDeletedIsVisible(){
        Driver.getDriver().findElement(By.cssSelector("a[href='/delete_account']")).click();
        BrowserUtils.waitForVisibility(By.cssSelector("h2[class='title text-center'] b"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("h2[class='title text-center'] b")).getText();
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-primary")).click();
        Assert.assertEquals(verifyingString,"ACCOUNT DELETED!");
    }
    @AfterSuite
    public void teardown(){
        Driver.getDriver().quit();
    }
}
