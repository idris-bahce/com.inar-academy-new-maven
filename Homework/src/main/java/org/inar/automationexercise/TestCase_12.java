package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.ArrayList;

public class TestCase_12 extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    private static final ArrayList<String> nameOfItems = new ArrayList<>();
    private static final ArrayList<String> priceOfItems = new ArrayList<>();
    private static final ArrayList<String> quantityOfItems = new ArrayList<>();
    private static final ArrayList<String> totalPriceOfItems = new ArrayList<>();
    final int quantityWeAdd = 1;

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
    public void verifyBothProductsAreAddedToCart() {
        pages.getHomePage().navigateTo(" Products");
        BrowserUtils.waitForVisibility(By.cssSelector(".title.text-center"), 2);
        Actions actions = new Actions(Driver.getDriver());
        //add first item
        WebElement element = Driver.getDriver().findElement(By.xpath("(//div[@class='productinfo text-center'])[1]"));
        actions.moveToElement(element).build().perform();
        BrowserUtils.waitForVisibility(By.xpath("(//a[contains(text(),'Add to cart')])[2]"), 2);
        Driver.getDriver().findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[2]")).click();
        BrowserUtils.waitForVisibility(By.cssSelector(".btn.btn-success.close-modal.btn-block"),2);
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();
        //add second item
        WebElement element2 = Driver.getDriver().findElement(By.xpath("(//div[@class='productinfo text-center'])[2]"));
        actions.moveToElement(element2).build().perform();
        BrowserUtils.waitForVisibility(By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[4]"), 2);
        Driver.getDriver().findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[4]")).click();
        BrowserUtils.waitForVisibility(By.cssSelector(".btn.btn-success.close-modal.btn-block"),2);
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();
        //store price of the items
        String price1 = Driver.getDriver().findElement(By.xpath("(//h2[contains(text(),'Rs. 500')])[1]")).getText();
        String price2 = Driver.getDriver().findElement(By.xpath("(//h2[contains(text(),'Rs. 400')])[1]")).getText();
        priceOfItems.add(price1);
        priceOfItems.add(price2);

        //store quantity of the items. Here we know that we have one item each

        quantityOfItems.add(quantityWeAdd + "");
        quantityOfItems.add(quantityWeAdd + "");

        //store total prices
        totalPriceOfItems.add((price1));
        totalPriceOfItems.add(price2);
        for (int i = 3; i < 6; i += 2) {//here we are adding the names of the first two items to an arraylist
            nameOfItems.add(Driver.getDriver().findElement(By.xpath("(//div/p)[" + i + "]")).getText());
        }
        pages.getProductPage().navigateTo(" Cart");

        for (int i = 0; i < nameOfItems.size(); i++) {
            softAssert.assertEquals(Driver.getDriver().findElement(By.xpath("(//h4/a[1])[" + (i + 1) + "]")).getText(), nameOfItems.get(i));
        }
    }

    @Test
    public void verifyTheirPricesQuantityAndTotalPrice() {
        String priceInCart1 = Driver.getDriver().findElement(By.xpath("(//p[contains(text(),'Rs. 500')])[1]")).getText();
        String priceInCart2 = Driver.getDriver().findElement(By.xpath("(//p[contains(text(),'Rs. 400')])[1]")).getText();
        softAssert.assertEquals(priceInCart1, priceOfItems.get(0));
        softAssert.assertEquals(priceInCart2, priceOfItems.get(1));

        String quantityInCart1 = Driver.getDriver().findElement(By.xpath("(//button[@class='disabled'][normalize-space()='1'])[1]")).getText();
        String quantityInCart2 = Driver.getDriver().findElement(By.xpath("(//button[@class='disabled'][normalize-space()='1'])[2]")).getText();
        softAssert.assertEquals(quantityInCart1, quantityOfItems.get(0));
        softAssert.assertEquals(quantityInCart2, quantityOfItems.get(1));

        String totalPrice1 = Driver.getDriver().findElement(By.xpath("(//p[@class='cart_total_price'][normalize-space()='Rs. 500'])[1]")).getText();
        String totalPrice2 = Driver.getDriver().findElement(By.xpath("(//p[@class='cart_total_price'][normalize-space()='Rs. 400'])[1]")).getText();
        softAssert.assertEquals(totalPrice1, totalPriceOfItems.get(0));
        softAssert.assertEquals(totalPrice2, totalPriceOfItems.get(1));
        softAssert.assertAll();
    }

    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }

}
