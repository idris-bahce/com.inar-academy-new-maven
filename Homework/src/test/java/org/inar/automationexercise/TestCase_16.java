package org.inar.automationexercise;

import org.inar.pages.HomePage;
import org.inar.utilities.ConfigurationReader;
import org.inar.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.inar.utilities.BrowserUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TestCase_16 extends BaseTest{
    @BeforeSuite
    public void setUpSuite() {
        // code that is executed before the entire test suite
        String URL = ConfigurationReader.getProperty("url");
        String browser = ConfigurationReader.getProperty("browser");
        String environment = ConfigurationReader.getProperty("environment");
        Driver.getDriver().get(URL);
        System.out.println("::::::Test Information ::::::\n\tURL :" + URL + "\n\tBrowser :" + browser + "\n\tEnvironment :" + environment);
        BrowserUtils.wait(1);
    }
    @Test
    public void verifyHomePageIsVisible(){
        String verifyingWord = Driver.getDriver().findElement(By.xpath("//li/a[@style='color: orange;']")).getText();
        Assert.assertEquals(verifyingWord,"Home");
    }
    @Test
    public void goToLoginSingUpPage(){
        HomePage homePage = new HomePage();
        homePage.clickToSignupAndLoginButton();
        BrowserUtils.waitForVisibility(By.cssSelector("div[class='login-form'] h2"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='login-form'] h2")).getText();
        Assert.assertEquals(verifyingString,"Login to your account");
    }
    @Test
    public void verifyThatLoggedInAsUsernameIsVisible(){

        pages.getLoginPage().sendEmail("bahce@bahce");
        pages.getLoginPage().sendPassword("bahce");
        pages.getLoginPage().login();
        BrowserUtils.waitForVisibility(By.cssSelector("li:nth-child(10) a:nth-child(1)"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("li:nth-child(10) a:nth-child(1)")).getText();
        Assert.assertEquals(verifyingString,"Logged in as bahce");
    }
    @Test
    public void  verifyThatCartPageIsDisplayed(){
        Driver.getDriver().findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[1]")).click();
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();
        Driver.getDriver().findElement(By.xpath("(//div/a[@class='btn btn-default add-to-cart'])[3]")).click();
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();
        pages.getHomePage().navigateTo(" Cart");
        BrowserUtils.waitForVisibility(By.cssSelector(".active"),1);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector(".active")).getText();
        Assert.assertEquals(verifyingString,"Shopping Cart");
    }
    @Test
    public void verifyAddressDetailsAndReviewYourOrder() throws FileNotFoundException {
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-default.check_out")).click();
        List<WebElement> list = Driver.getDriver().findElements(By.xpath("//ul[@class='address item box']/li"));
        List<String> listOfInformation = list.stream().map(s->s.getText()).collect(Collectors.toList());
        listOfInformation.remove(0);//we are removing the "YOUR DELIVERY ADDRESS"
        File file = new File("C:\\Users\\idris\\Desktop\\NewMaven\\com.inar-academy-new-maven\\Homework\\PersonalInformation");
        Scanner input = new Scanner(file);
        int index = 0;
        while (input.hasNext()){
            String info = input.nextLine();
            if (!info.equals(listOfInformation.get(index))){
                Assert.fail();
            }
            index++;
        }
    }
    @Test
    public void verifySuccessMessageOurOrderHasBeenPlacedSuccessfully(){
        Driver.getDriver().findElement(By.cssSelector("textarea[name='message']")).sendKeys("hope my thins comes fast");
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-default.check_out")).click();
        Driver.getDriver().findElement(By.cssSelector("input[name='name_on_card']")).sendKeys("idris");
        Driver.getDriver().findElement(By.cssSelector("input[name='card_number']")).sendKeys("1234567878964123");
        Driver.getDriver().findElement(By.cssSelector("input[placeholder='ex. 311']")).sendKeys("123");
        Driver.getDriver().findElement(By.cssSelector("input[placeholder='MM']")).sendKeys("12");
        Driver.getDriver().findElement(By.cssSelector("input[placeholder='YYYY']")).sendKeys("2025");
        Driver.getDriver().findElement(By.cssSelector("#submit")).click();
        BrowserUtils.waitForVisibility(By.xpath("//div[class='alert-success alert']"),2);
        String string = Driver.getDriver().findElement(By.xpath("//div[class='alert-success alert']")).getText();
        Assert.assertEquals(string,"Your order has been placed successfully!");
    }
    @Test
    public void verifyACCOUNTDELETEDAndClickContinueButton(){
        Driver.getDriver().findElement(By.cssSelector("a[href='/delete_account']")).click();
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("h2[class='title text-center'] b")).getText();
        Assert.assertEquals(verifyingString,"ACCOUNT DELETED!");
    }
    @AfterSuite
    public void teardown() {
        Driver.getDriver().quit();
    }
}
