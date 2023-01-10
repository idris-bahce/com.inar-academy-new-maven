package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{
    @FindBy(css = "#search_product")
    private WebElement searchBox;

    public void setSearchBox(String item){
        searchBox.sendKeys(item);
    }
}
