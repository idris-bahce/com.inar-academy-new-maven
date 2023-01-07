package org.inaracademy;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void submitOrder() throws IOException, InterruptedException {
        final String productName = "ZARA COAT 3";
        CataloguePage cataloguePage = landingPage.loginApplication("yavuzcengaver@gmail.com","Tester4256");

        List<WebElement> products = cataloguePage.getProducts();
        cataloguePage.getProductName(productName);
        cataloguePage.addProductToCart(productName);
        CartPage cartPage = cataloguePage.goToCartPage();

        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("tur");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equals("THANKYOU FOR THE ORDER."));



    }
}
