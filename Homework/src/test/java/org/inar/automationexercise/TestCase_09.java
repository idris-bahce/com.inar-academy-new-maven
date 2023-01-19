package org.inar.automationexercise;

import org.inar.utilities.BrowserUtils;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TestCase_09 extends BaseTest{
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
    public void verifySearchedProductsIsVisible(){
        Driver.getDriver().findElement(By.id("search_product")).sendKeys("top");
        Driver.getDriver().findElement(By.id("submit_search")).click();
        BrowserUtils.waitForVisibility(By.cssSelector(".title.text-center"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".title.text-center")).getText();
        Assert.assertEquals(verifyingString,"SEARCHED PRODUCTS");
    }
    @Test
    public void verifyAllTheProductsRelatedToSearchAreVisible(){
        List<WebElement> listOfWebElements = Driver.getDriver().findElements(By.xpath("//img[@alt='ecommerce website products']/following-sibling::p"));
        List<String> listOfItems = listOfWebElements.stream().map(s->s.getText()).collect(Collectors.toList());
        List<String> listOfItemsWhichContainsTop = listOfItems.stream().filter(s-> s.contains("Top")).collect(Collectors.toList());
        Assert.assertEquals(listOfItems.size(),listOfItemsWhichContainsTop.size());//if there are no "Top" in items' name, then there are some unrelated items

//        for (int i = 0; i < listOfItems.size(); i++) {
//            if (!listOfItems.get(i).toLowerCase().contains("top")){
//                Assert.assertFalse(true);
//            }
//        }
    }
    @AfterSuite
    public void teardown(){
        Driver.getDriver().quit();
    }

}
