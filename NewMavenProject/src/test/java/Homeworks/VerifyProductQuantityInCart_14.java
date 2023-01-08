package Homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class VerifyProductQuantityInCart_14 {
    WebDriver driver;

    @Test
    public void homePageVisibility(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        File file = new File("C:\\Users\\idris\\Desktop\\extension_5_3_3_0 (1).crx");
        options.addExtensions(file);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        String  validationString = driver.findElement(By.xpath("//li/a[@style='color: orange;']")).getText();
        Assert.assertEquals(validationString,"Home");
    }

    @Test
    public void addItemsToCart(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        for (int i = 1; i < 4; i++) {
            driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])["+i+"]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-success close-modal btn-block']")));
            driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']")).click();
        }
        driver.findElement(By.linkText("Cart")).click();


    }
}
