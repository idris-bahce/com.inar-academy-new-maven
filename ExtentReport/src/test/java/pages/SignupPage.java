package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class SignupPage extends BasePage {


    //Fill details: Title, Name, Email, Password, Date of birth
    @FindBy(xpath = "//*[@id='form']/div/div/div/div[1]/h2")
    private WebElement enterAccountInformationTitle;

    @FindBy(xpath = "//*[@id='id_gender1']")
    private WebElement id_genderMR;

    @FindBy(xpath = "//*[@id='id_gender2']")
    private WebElement id_genderMRS;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordBox;

    @FindBy(xpath = "//*[@data-qa='days']")
    private WebElement dateOfBirt_DAYs;
    @FindBy(xpath = "//*[@data-qa='months']")
    private WebElement dateOfBirt_MONTHs;

    @FindBy(xpath = "//*[@data-qa='years']")
    private WebElement dateOfBirt_YEARs;

    @FindBy(xpath = "//*[@id='header']/div/div/div/div[2]/div/ul/li[2]/a")
    private WebElement productsPageButton;

    public SignupPage() {
    }


    public String getEnterAccountInformationTitle() {
        return enterAccountInformationTitle.getText();

    }

    public void selectTitleMen() {
        id_genderMR.click();
    }

    public void selectTitleWomen() {
        id_genderMRS.click();
    }

    public void setPassword(String password) {
        passwordBox.sendKeys(password);
    }

    public void setDateOfBirt(String day, String month, String year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    private void setDay(String day) {
        Select select = new Select(dateOfBirt_DAYs);
        select.selectByVisibleText(day);
    }

    private void setMonth(String month) {
        Select select = new Select(dateOfBirt_MONTHs);
        select.selectByVisibleText(month);
    }

    private void setYear(String year) {
        Select select = new Select(dateOfBirt_YEARs);
        select.selectByVisibleText(year);

    }

    public void selectProductsPageButton() {
        productsPageButton.click();
    }
}
