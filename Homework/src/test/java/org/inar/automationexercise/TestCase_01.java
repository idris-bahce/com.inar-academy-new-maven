package org.inar.automationexercise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

public class TestCase_01 extends BaseTest{

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
    public void verifyNewUserSignupVisible(){
        HomePage homePage = new HomePage();
        homePage.clickToSignupAndLoginButton();
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("div[class='signup-form'] h2")).getText();
        Assert.assertEquals(verifyingString,"New User Signup!");
    }
    @Test
    public void verifyThatEnterAccountInformationIsVisible(){
        SignupPage signupPage = new SignupPage();
        signupPage.writeEmail("idris@idris123");
        signupPage.writeName("idris");
        signupPage.clickSignupButton();
        BrowserUtils.waitForVisibility(By.xpath("(//div/h2[@class='title text-center'])[1]"),2);
        String verifyingString = Driver.getDriver().findElement(By.xpath("(//div/h2[@class='title text-center'])[1]")).getText();
        Assert.assertEquals(verifyingString,"ENTER ACCOUNT INFORMATION");
    }
    @Test
    public void verifyThatAccountCreatedIsVisible() {
        SignupPage signupPage = new SignupPage();
        signupPage.selectTitleMen();
        signupPage.setPassword("123456");
        signupPage.setDateOfBirt("4","May","1990");
        signupPage.signupForOurNewsletterBox();
        signupPage.receiveSpecialOffersFromOurTeamBox();
        Driver.getDriver().findElement(By.id("first_name")).sendKeys("idris");
        Driver.getDriver().findElement(By.id("first_name")).sendKeys("idris");
        Driver.getDriver().findElement(By.id("last_name")).sendKeys("ace");
        Driver.getDriver().findElement(By.id("company")).sendKeys("inar");
        Driver.getDriver().findElement(By.id("address1")).sendKeys("Cal sok. Silver aven.");
        Driver.getDriver().findElement(By.id("address2")).sendKeys("Cal sok. New aven.");
        Driver.getDriver().findElement(By.id("state")).sendKeys("Pencap");
        Driver.getDriver().findElement(By.id("state")).sendKeys("Pencap");
        Driver.getDriver().findElement(By.id("city")).sendKeys("Delhi");
        Driver.getDriver().findElement(By.id("zipcode")).sendKeys("03300");
        Driver.getDriver().findElement(By.id("mobile_number")).sendKeys("1425369875");
        signupPage.createAccountButton();
        BrowserUtils.waitForVisibility(By.cssSelector("h2[class='title text-center'] b"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("h2[class='title text-center'] b")).getText();
        Assert.assertEquals(verifyingString,"ACCOUNT CREATED!");
    }
    @Test
    public void verifyThatLoggedInAsUsernameIsVisible(){
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-primary")).click();
        BrowserUtils.waitForVisibility(By.xpath("//li/a/i[@class='fa fa-user']/parent::a"),2);
        String verifyingString = Driver.getDriver().findElement(By.xpath("//li/a/i[@class='fa fa-user']/parent::a")).getText();
        Assert.assertEquals(verifyingString,"Logged in as idris");
    }
    @Test
    public void verifyThatAccountDeletedIsVisible(){
        Driver.getDriver().findElement(By.cssSelector("a[href='/delete_account']")).click();
        BrowserUtils.waitForVisibility(By.cssSelector("h2[class='title text-center'] b"),2);
        String verifyingString = Driver.getDriver().findElement(By.cssSelector("h2[class='title text-center'] b")).getText();
        Driver.getDriver().findElement(By.cssSelector(".btn.btn-primary")).click();
        Assert.assertEquals(verifyingString,"ACCOUNT DELETED!");
    }
    @AfterSuite
    public void teardown(){
        Driver.getDriver().quit();
    }

}
