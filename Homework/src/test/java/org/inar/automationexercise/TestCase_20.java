package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class TestCase_20 extends BaseTest{
    List<String> listOfItems;
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
    public void verifySEARCHEDPRODUCTSIsVisible(){
        Driver.getDriver().findElement(By.cssSelector("#search_product")).sendKeys("jean");
        Driver.getDriver().findElement(By.cssSelector("#submit_search")).click();
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".title.text-center")).getText();
        Assert.assertEquals(verifyingString,"SEARCHED PRODUCTS");
    }
    @Test
    public void verifyAllTheProductsRelatedToSearchAreVisible(){
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("(//div/img)/following-sibling::p"));
        List<String> namesOfSearchedItems = elements.stream().map(s->s.getText()).collect(Collectors.toList());
        for (int i = 0; i < namesOfSearchedItems.size(); i++) {
            if (!namesOfSearchedItems.get(i).contains("Jean")){
                Assert.fail();
            }
        }
        Assert.assertTrue(true);
    }
    @Test
    public void clickCartButtonAndVerifyThatProductsAreVisibleInCart(){
        List<WebElement> elementsToClick = Driver.getDriver().findElements(By.xpath("//a[contains(text(),'Add to cart')]"));
        for (int i = 1; i < elementsToClick.size(); i+=2) {
            Driver.getDriver().findElement(By.xpath("(//a[contains(text(),'Add to cart')])[" + i + "]")).click();
            BrowserUtils.waitForVisibility(By.cssSelector(".btn.btn-success.close-modal.btn-block"),2);
            Driver.getDriver().findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();
        }
        pages.getHomePage().navigateTo(" Cart");
        List<WebElement> listOfItemsAsWebElement = Driver.getDriver().findElements(By.cssSelector("h4 a"));
        listOfItems = listOfItemsAsWebElement.stream().map(s->s.getText()).collect(Collectors.toList());
        for (int i = 0; i < listOfItems.size(); i++) {
            if (!listOfItems.get(i).contains("Jean")){
                Assert.fail();
            }
        }
        Assert.assertTrue(true);
    }
    @Test
    public void verifyThatThoseProductsAreVisibleInCartAfterLoginAsWell(){
        pages.getHomePage().navigateTo(" Signup / Login");
        pages.getLoginPage().sendEmail("bahce@bahce1");
        pages.getLoginPage().sendPassword("bahce");
        pages.getLoginPage().login();
        BrowserUtils.waitForVisibility(By.cssSelector("li:nth-child(10) a:nth-child(1)"),2);
        pages.getHomePage().navigateTo(" Cart");
        for (int i = 0; i < listOfItems.size(); i++) {
            if (!listOfItems.get(i).contains("Jean")){
                Assert.fail();
            }
        }
        Assert.assertTrue(true);
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }
}
