package org.inaracademy;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CataloguePage extends AbstractComponent {
    private WebDriver driver;

    @FindBy(css = ".mb-3")
    private List<WebElement> products;
    @FindBy(css = ".ng-animating")
    private WebElement spinner;
    private By waitForElement = By.cssSelector(".mb-3");
    private By addToCart = By.cssSelector(".card-body button:last-of-type");
    private By waitAfterAddToCart = By.cssSelector("toast-container");
    public CataloguePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public List<WebElement> getProducts() {
        waitForTheElementToAppear(waitForElement);
        return products;
    }

    public WebElement getProductName(String productName){
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText()
                .equals(productName)).findFirst().orElse(null);
        return prod;
    }
    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductName(productName);
        prod.findElement(addToCart).click();
       // waitForTheElementToAppear(waitAfterAddToCart);
        waitForTheElementToDisappear(spinner);
    }
}
