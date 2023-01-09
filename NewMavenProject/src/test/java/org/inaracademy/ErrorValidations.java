package org.inaracademy;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorHandling"})
    public void loginErrorValidation()  {

        landingPage.loginApplication("yavuzcengaver@gmail.com","Tester6");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }
    @Test
    public void productErrorValidation() throws IOException, InterruptedException {
        final String productName = "ZARA COAT 3";
        CataloguePage cataloguePage = landingPage.loginApplication("fatihmehmet@gmail.com","Tester4256");

        List<WebElement> products = cataloguePage.getProducts();
        cataloguePage.getProductName(productName);
        cataloguePage.addProductToCart(productName);
        CartPage cartPage = cataloguePage.goToCartPage();

        boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
