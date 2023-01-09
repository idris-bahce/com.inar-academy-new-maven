package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BrowserUtils;


public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")
    private WebElement SignupLoginButton;

    @FindBy(xpath = "//*[@id='header']/div/div/div/div[2]/div/ul/li[2]/a")
    private WebElement productButton;


    public void clickSignupLoginButton() {
        SignupLoginButton.click();
    }

    public void clickProductButton(){
        BrowserUtils.verifyElementDisplayed(productButton);
        productButton.click();
    }

}
