package StepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.inaracademy.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public CataloguePage cataloguePage;

    ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void  logged_In_With_Username_Name_And_Password_(String username, String password){
        cataloguePage = landingPage.loginApplication(username,password);
    }
    @When("^I add product (.+) to cart$")
    public void i_Add_Product_To_Cart(String productName) throws InterruptedException {
        List<WebElement> products = cataloguePage.getProducts();
        cataloguePage.addProductToCart(productName);
    }
    @And("^Checkout (.+) and submit the order$")
    public void checkout_ProductName_And_Submit_The_Oder(String productName){
        CartPage cartPage = cataloguePage.goToCartPage();

        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("tur");
        confirmationPage = checkoutPage.submitOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_Displayed_ConfirmationPage(String string){

        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equals(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String arg0) {
        Assert.assertEquals(arg0,landingPage.getErrorMessage());
        driver.close();
    }
}
