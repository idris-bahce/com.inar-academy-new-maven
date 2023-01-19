package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

public class TestCase_08 extends BaseTest{
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
    public void verifyUserIsNavigatedToAllProductsPageSuccessfully(){
        pages.getHomePage().navigateTo(" Products");
        BrowserUtils.waitForVisibility(By.cssSelector(".title.text-center"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".title.text-center")).getText();
        Assert.assertEquals(verifyingString,"ALL PRODUCTS");
    }
    @Test
    public void verifyThatDetailsAreVisible(){// product name, category, price, availability, condition, brand
        Driver.getDriver().findElement(By.cssSelector("a[href='/product_details/1']")).click();
        SoftAssert softAssert = new SoftAssert();
        String productName = Driver.getDriver().findElement(By.cssSelector("div[class='product-information'] h2")).getText();
        softAssert.assertEquals(productName,"Blue Top");
        String category = Driver.getDriver().findElement(By.xpath("(//p[normalize-space()='Category: Women > Tops'])[1]")).getText();
        softAssert.assertEquals(category,"Category: Women > Tops");
        String price = Driver.getDriver().findElement(By.cssSelector("div[class='product-information'] span span")).getText();
        softAssert.assertEquals(price,"Rs. 500");
        String availability = Driver.getDriver().findElement(By.xpath("//b[normalize-space()='Availability:']")).getText();
        softAssert.assertEquals(availability,"Availability:");
        String condition = Driver.getDriver().findElement(By.xpath("//b[normalize-space()='Condition:']")).getText();
        softAssert.assertEquals(condition,"Condition:");
        String brand = Driver.getDriver().findElement(By.xpath("(//b[normalize-space()='Brand:'])[1]")).getText();
        softAssert.assertEquals(brand,"Brand:");
    }
    @AfterSuite
    public void teardown(){
        Driver.getDriver().quit();
    }

}
