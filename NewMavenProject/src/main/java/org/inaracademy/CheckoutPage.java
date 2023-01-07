package org.inaracademy;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
    private WebDriver driver;
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement country;
    @FindBy(css = ".btnn")
    private WebElement submit;
    @FindBy(xpath = "(//button[@type='button'])[1]")
    private WebElement selectCountry;
    By results = By.cssSelector(".list-group");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitForTheElementToAppear(results);
        selectCountry.click();
    }
    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }
}
