package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;

public class TestCase_21 extends BaseTest{
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
    public void verifyUserIsNavigatedToALLPRODUCTSPageSuccessfully(){
        pages.getHomePage().navigateTo(" Products");
        BrowserUtils.waitForVisibility(By.cssSelector(".title.text-center"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".title.text-center")).getText();
        Assert.assertEquals(verifyingString,"ALL PRODUCTS");
    }
    @Test
    public void verifyWriteYourReviewIsVisible(){
        Driver.getDriver().findElement(By.cssSelector("a[href='/product_details/7']")).click();
        BrowserUtils.waitForVisibility(By.cssSelector("a[href='#reviews']"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("a[href='#reviews']")).getText();
        Assert.assertEquals(verifyingString,"WRITE YOUR REVIEW");
    }
    @Test
    public void  verifySuccessMessageThankYouForYourReview(){
        Driver.getDriver().findElement(By.cssSelector("#name")).sendKeys("idr");
        Driver.getDriver().findElement(By.cssSelector("#email")).sendKeys("idr@idr");
        Driver.getDriver().findElement(By.cssSelector("#review")).sendKeys("it is a very good product");
        Driver.getDriver().findElement(By.cssSelector("#button-review")).click();
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='alert-success alert'] span")).getText();
        Assert.assertEquals(verifyingString,"Thank you for your review.");
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }
}
