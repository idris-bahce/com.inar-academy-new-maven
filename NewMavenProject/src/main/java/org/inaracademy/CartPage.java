package org.inaracademy;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    @FindBy(css = ".totalRow button")
    private WebElement checkout;
    @FindBy(css = ".cart h3")
    private List<WebElement> productTitles;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public boolean verifyProductDisplay(String productName){
        return  productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
    }
    public CheckoutPage goToCheckout(){
        checkout.click();
        return new CheckoutPage(driver);
    }
}
