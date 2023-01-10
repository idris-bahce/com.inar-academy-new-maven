package utilities;

import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SignupPage;

public class Pages {

    private HomePage homePage;

    private LoginPage loginPage;

    private ProductPage productPage;

    private SignupPage signupPage;

    public Pages() {
        this.homePage = new HomePage();
        this.loginPage = new LoginPage();
        this.productPage = new ProductPage();
        this.signupPage = new SignupPage();
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
}
