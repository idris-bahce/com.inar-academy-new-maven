package org.inaracademy;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    private WebDriver driver;
    @FindBy(tagName = "h1")
    private WebElement confirmationMessage;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String getConfirmationMessage(){
        return confirmationMessage.getText();
    }
}
