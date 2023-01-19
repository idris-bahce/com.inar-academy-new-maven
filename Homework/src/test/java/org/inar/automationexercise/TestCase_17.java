package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;

public class TestCase_17 extends BaseTest{
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
    public void  verifyThatCartPageIsDisplayed(){
        Driver.getDriver().findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[1]")).click();
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();
        Driver.getDriver().findElement(By.xpath("(//div/a[@class='btn btn-default add-to-cart'])[3]")).click();
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();
        pages.getHomePage().navigateTo(" Cart");
        BrowserUtils.waitForVisibility(By.cssSelector(".active"),1);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".active")).getText();
        Assert.assertEquals(verifyingString,"Shopping Cart");
    }
    @Test
    public void verifyThatProductIsRemovedFromTheCart(){
        Driver.getDriver().findElement(By.cssSelector("tr[id='product-1'] a[class='cart_quantity_delete']")).click();
        Driver.getDriver().findElement(By.cssSelector("tr[id='product-2'] a[class='cart_quantity_delete']")).click();
        BrowserUtils.waitForVisibility(By.cssSelector("p[class='text-center'] b"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("p[class='text-center'] b")).getText();
        Assert.assertEquals(verifyingString,"Cart is empty!");
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }
}
