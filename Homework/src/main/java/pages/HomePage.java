package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(css = "a[href='/products']")
    private WebElement productsButton;

    @FindBy(css = "a[href='/login']")
    private WebElement signupButton;

    public void clickToProducts() {
        productsButton.click();
    }
    public void clickToSignupAndLoginButton() {
        signupButton.click();
    }

}
