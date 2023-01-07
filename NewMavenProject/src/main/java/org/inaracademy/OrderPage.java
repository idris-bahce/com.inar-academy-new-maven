package org.inaracademy;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = ".totalRow button")
    private WebElement checkout;
    @FindBy(css = "tr td:nth-child(3)")
    public List<WebElement> orderNames;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public boolean verifyOrderDisplay(String productName){
        return  orderNames.stream().anyMatch(orderProduct->orderProduct.getText().equalsIgnoreCase(productName));
    }

}
