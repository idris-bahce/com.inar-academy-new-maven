package utilities;

import pages.*;

public class Pages {

    private HomePage homePage;

    private LoginPage loginPage;

    private ProductPage productPage;

    private SignupPage signupPage;
    private ContactUsPage contactUsPage;

    public Pages() {
        this.homePage = new HomePage();
        this.loginPage = new LoginPage();
        this.productPage = new ProductPage();
        this.signupPage = new SignupPage();
        this.contactUsPage = new ContactUsPage();
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public ProductPage getProductPage() {
        return productPage;
    }

    public SignupPage getSignupPage() {
        return signupPage;
    }
    public ContactUsPage getContactUsPage(){
        return contactUsPage;
    }
}
