package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

public class TestCase_06 extends BaseTest{
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
    public void verifyGetInTouchIsVisible(){
        pages.getHomePage().navigateTo(" Contact us");
        BrowserUtils.waitForVisibility(By.cssSelector("div[class='contact-form'] h2[class='title text-center']"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='contact-form'] h2[class='title text-center']")).getText();
        Assert.assertEquals(verifyingString,"GET IN TOUCH");
    }
    @Test
    public void verifySuccessMessageSuccessYourDetailsHaveBeenSubmittedSuccessfullyIsVisible(){
        pages.getContactUsPage().setSendName("bahce");
        pages.getContactUsPage().setSendEmail("bahce@bahce");
        pages.getContactUsPage().setSendSubject("contact");
        pages.getContactUsPage().setSendMessage("Please contact me!");
        pages.getContactUsPage().setSendFile("C:\\Users\\idris\\Desktop\\Git komutları.txt");
        pages.getContactUsPage().setSubmit();
        Driver.getDriver().switchTo().alert().accept();
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".status.alert.alert-success")).getText();
        Assert.assertEquals(verifyingString,"Success! Your details have been submitted successfully.");


    }
    @Test
    public void verifyHomePageIsVisible2() {
        Driver.getDriver().findElement(By.cssSelector("a[class='btn btn-success'] span")).click();
        String verifyingWord = Driver.getDriver().findElement(By.xpath("//li/a[@style='color: orange;']")).getText();
        Assert.assertEquals(verifyingWord, "Home");
    }
    @AfterSuite
    public void teardown(){
        Driver.getDriver().quit();
    }
}
