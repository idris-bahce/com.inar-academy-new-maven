package org.inar.automationexercise;


import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

public class RegistrationTests extends BaseTest {

    SoftAssert softAssert = new SoftAssert(); // We should use soft assertion because in this test case we have multiple cases to test

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


    @Test(priority = 0)
    public void TestCase_1_Register_User() {

        //Launch browser and Navigate to url 'http://automationexercise.com'
        //Click on 'Signup / Login' button

        pages.getHomePage().clickSignupLoginButton();

        String newUserSignupMessage = pages.getLoginPage().getNewUserSignupMessage();

        //Verify 'New User Signup!' is visible
        softAssert.assertEquals(newUserSignupMessage, "New User Signup!", "Test Case 1 - Verify 'New User Signup!' is visible");

        //Enter name and email address
        pages.getLoginPage().setSignupNewUserName("Fatma");

        pages.getLoginPage().setSignupEmailAddressBox("QWERT@gmail.com");
        // Click 'Signup' button
        pages.getLoginPage().clickSignupButton();

        // Verify that 'ENTER ACCOUNT INFORMATION' is visible
        String actualEnterAccountInformationTitle = pages.getSignupPage().getEnterAccountInformationTitle();
        softAssert.assertEquals(actualEnterAccountInformationTitle, "ENTER ACCOUNT INFORMATION", "ERROR : Test Case 1 - Verify that 'ENTER ACCOUNT INFORMATION' is visible\n");


        // Fill details: Title, Name, Email, Password, Date of birth
        pages.getSignupPage().selectTitleWomen();
        pages.getSignupPage().setPassword("hacasdf");
        pages.getSignupPage().setDateOfBirt("12", "April", "1945");

        pages.getSignupPage().selectProductsPageButton();
        pages.getProductsPage().searchProduct("Fancy Green Top");

        BrowserUtils.wait(6);

        softAssert.assertAll(); // NOTE: don't import to execute 'assertAll' at the end.

    }


    @AfterSuite
    public void afterTest() {
        // Perform cleanup tasks or generate test reports here
        Driver.getDriver().quit();
    }
}
