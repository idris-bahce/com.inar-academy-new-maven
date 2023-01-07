package org.inaracademy;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    private WebDriver driver;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    private WebElement errorMessage;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
    public CataloguePage loginApplication(String email, String password){
        this.userEmail.sendKeys(email);
        this.password.sendKeys(password);
        this.submit.click();
        return new CataloguePage(driver);
    }
    public String getErrorMessage(){
        waitForTheElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
