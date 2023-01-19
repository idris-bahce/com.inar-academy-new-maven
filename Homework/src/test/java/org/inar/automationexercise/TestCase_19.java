package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;

public class TestCase_19 extends BaseTest{
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
    public void verifyThatBrandsAreVisibleOnLeftSideBar(){
        pages.getHomePage().navigateTo(" Products");
        String verifyingString = Driver.getDriver().findElement(By.xpath("(//h2[normalize-space()='Brands'])[1]")).getText();
        Assert.assertEquals(verifyingString,"BRANDS");
    }
    @Test
    public void verifyThatUserIsNavigatedToBrandPageAndBrandProductsAreDisplayed(){
        Driver.getDriver().findElement(By.cssSelector("a[href='/brand_products/H&M']")).click();//navigate to H&M
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".title.text-center")).getText();
        Assert.assertEquals(verifyingString,"BRAND - H&M PRODUCTS");
    }
    @Test
    public void verifyThatUserIsNavigatedToThatBrandPageAndCanSeeProducts(){
        Driver.getDriver().findElement(By.cssSelector("a[href='/brand_products/Polo']")).click();//navigate to POLO
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".title.text-center")).getText();
        Assert.assertEquals(verifyingString,"BRAND - POLO PRODUCTS");
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }
}
