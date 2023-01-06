package org.inaracademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
        final String zaraCoat = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client/");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        login(driver);

        addZaraCoatToChart(driver,zaraCoat);

        waitingForLoadingSection(driver,wait);

        goToCartAndValidateTheItems(driver,zaraCoat);

        pickUpYourCountry(driver,wait);

        validateNewPage(driver,wait);

        driver.close();
    }

    private static void validateNewPage(WebDriver driver, WebDriverWait wait) {
        String text = driver.findElement(By.tagName("h1")).getText();
        Assert.assertTrue(text.equals("THANKYOU FOR THE ORDER."));
    }

    private static void pickUpYourCountry(WebDriver driver,WebDriverWait wait) {
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"turk").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(" .list-group")));
        driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
        driver.findElement(By.cssSelector(".btnn")).click();
    }

    private static void goToCartAndValidateTheItems(WebDriver driver,String zaraCoat) {
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(zaraCoat));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();
    }

    private static void waitingForLoadingSection(WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
    }

    private static void addZaraCoatToChart(WebDriver driver,String zaraCoat) {
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement coat = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText()
                .equals(zaraCoat)).findFirst().orElse(null);
        coat.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    }

    private static void login(WebDriver driver) {
        driver.findElement(By.id("userEmail")).sendKeys("yavuzcengaver@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Tester4256");
        driver.findElement(By.id("login")).click();
    }
}
