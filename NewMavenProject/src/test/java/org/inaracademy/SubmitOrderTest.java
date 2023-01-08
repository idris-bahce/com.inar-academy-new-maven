package org.inaracademy;

import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    final String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        CataloguePage cataloguePage = landingPage.loginApplication(input.get("email"),input.get("password"));

        List<WebElement> products = cataloguePage.getProducts();
        cataloguePage.getProductName(input.get("product"));
        cataloguePage.addProductToCart(input.get("product"));
        CartPage cartPage = cataloguePage.goToCartPage();

        boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("tur");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equals("THANKYOU FOR THE ORDER."));
    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(){
        CataloguePage cataloguePage = landingPage.loginApplication("yavuzcengaver@gmail.com","Tester4256");
        OrderPage orderPage = cataloguePage.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\idris\\Desktop\\NewMaven\\com.inar-academy-new-maven\\NewMavenProject\\src\\test\\java\\Data\\PurchaseOrder.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
    //        HashMap<String,String> map = new HashMap<>();
//        map.put("email","yavuzcengaver@gmail.com");
//        map.put("password","Tester4256");
//        map.put("product","ZARA COAT 3");
//
//        HashMap<String,String> map2 = new HashMap<>();
//        map2.put("email","fatihmehmet@gmail.com");
//        map2.put("password","Tester4256");
//        map2.put("product","ADIDAS ORIGINAL");
}
