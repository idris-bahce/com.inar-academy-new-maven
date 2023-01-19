package org.inar.automationexercise;

import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase_13 extends BaseTest{

    final private String NUMBER_OF_ITEMS = "4";

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
    public void verifyProductDetailIsOpened(){
        pages.getHomePage().navigateTo(" Products");
        BrowserUtils.waitForVisibility(By.cssSelector(".title.text-center"), 2);
        Driver.getDriver().findElement(By.cssSelector("a[href='/product_details/1']")).click();//We are clicking "Blue Top"
        BrowserUtils.waitForVisibility(By.cssSelector("div[class='product-information'] h2"), 2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='product-information'] h2")).getText();
        Assert.assertEquals(verifyingString,"Blue Top");
    }
    @Test
    public void verifyThatProductIsDisplayedInCartPageWithExactQuantity(){
        //we are increasing the number to 4 here
        Driver.getDriver().findElement(By.cssSelector("#quantity")).clear();
        Driver.getDriver().findElement(By.cssSelector("#quantity")).sendKeys(NUMBER_OF_ITEMS);
        Driver.getDriver().findElement(By.cssSelector("button[type='button']")).click();
        BrowserUtils.waitForVisibility(By.xpath("(//u[normalize-space()='View Cart'])[1]"),1);
        Driver.getDriver().findElement(By.xpath("(//u[normalize-space()='View Cart'])[1]")).click();
        String verifyingNumber = Driver.getDriver().findElement(By.cssSelector(".disabled")).getText();
        Assert.assertEquals(verifyingNumber,NUMBER_OF_ITEMS);
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }

}
