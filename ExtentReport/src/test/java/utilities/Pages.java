package utilities;

import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.SignupPage;

public class Pages {
    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;

    private ProductsPage productsPage;


    public Pages() {
        this.homePage = new HomePage();
        this.loginPage = new LoginPage();
        this.signupPage = new SignupPage();
        this.productsPage = new ProductsPage();
    }

    public SignupPage getSignupPage() {
        return signupPage;
    }


    public HomePage getHomePage() {
        return homePage;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public ProductsPage getProductsPage() {
        return productsPage;
    }
}
