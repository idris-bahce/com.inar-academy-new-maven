package org.inar.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(css = "input[placeholder='Name']")
    private WebElement nameToSignup;

    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement emailToSignup;
    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(css = "input[data-qa='login-email']")
    private WebElement email;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement password;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginButton;

    public void sendEmail(String email){
        this.email.sendKeys(email);
    }
    public void sendPassword(String password){
        this.password.sendKeys(password);
    }
    public void login(){
        loginButton.click();
    }
    public void sendEmailToSignup(String email){
        this.emailToSignup.sendKeys(email);
    }
    public void sendNameToSignup(String name){
        this.nameToSignup.sendKeys(name);
    }
    public void clickToSignupButton(){
        this.signupButton.click();
    }
}
