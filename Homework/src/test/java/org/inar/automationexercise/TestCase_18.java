package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;

public class TestCase_18 extends BaseTest{
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
    public void verifyThatCategoriesAreVisibleOnLeftSideBar(){
        String verifyingString = Driver.getDriver().findElement(By.xpath("//div[@class='left-sidebar']/h2")).getText();
        Assert.assertEquals(verifyingString,"CATEGORY");
    }
    @Test
    public void verifyThatCategoryPageIsDisplayedAndConfirmTextWOMEN_DRESS_PRODUCTS(){
        Driver.getDriver().findElement(By.xpath("(//a[normalize-space()='Women'])[1]")).click();
        Driver.getDriver().findElement(By.xpath("(//a[contains(text(),'Dress')])[1]")).click();
        BrowserUtils.waitForVisibility(By.cssSelector(".title.text-center"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".title.text-center")).getText();
        Assert.assertEquals(verifyingString,"WOMEN - DRESS PRODUCTS");
    }
    @Test
    public void verifyThatUserIsNavigatedToThatCategoryPage(){
        Driver.getDriver().findElement(By.xpath("(//a[normalize-space()='Men'])[1]")).click();
        Driver.getDriver().findElement(By.xpath("(//a[normalize-space()='Tshirts'])[1]")).click();
        BrowserUtils.waitForVisibility(By.cssSelector(".title.text-center"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".title.text-center")).getText();
        Assert.assertEquals(verifyingString,"MEN - TSHIRTS PRODUCTS");
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }
}
