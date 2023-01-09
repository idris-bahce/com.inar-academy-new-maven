package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@data-qa='signup-name']")
    private WebElement signupNewUserNameBox;

    @FindBy(xpath = "//*[@data-qa='signup-email']")
    private WebElement signupEmailAddressBox;
    @FindBy(xpath = "//*[@data-qa='signup-button']")
    private WebElement signUpButton;

    @FindBy(xpath = "//*[@data-qa='login-email']")
    private WebElement loginEmailAddressBox;
    @FindBy(xpath = "//*[@data-qa='login-password']")
    private WebElement loginEmailPasswordBox;
    @FindBy(xpath = "//*[@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='form']/div/div/div[3]/div")
    private WebElement newUserSignupMessageWebElement;


    public void setSignupEmailAddressBox(String emailAddress) {
        signupEmailAddressBox.sendKeys(emailAddress);
    }

    public void clickSignupButton() {
        signUpButton.click();
    }


    public void setLoginEmailAddress(String emailAddress) {
        loginEmailAddressBox.sendKeys(emailAddress);
    }

    public void setLoginEmailPassword(String password) {
        loginEmailPasswordBox.sendKeys(password);
    }


    public void clickLoginButton() {
        loginButton.click();
    }


    public String getNewUserSignupMessage() {
        return newUserSignupMessageWebElement.getText();
    }

    public void setSignupNewUserName(String newUserName) {
        signupNewUserNameBox.sendKeys(newUserName);
    }
}

