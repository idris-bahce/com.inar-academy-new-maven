package Homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class VerifyProductQuantityInCart_13 {
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
    public void verificationOfProductView(){
        Actions a = new Actions(driver);
        WebElement viewProduct = driver.findElement(By.xpath("//li/a[@href='/product_details/7']"));
        a.click(viewProduct).build().perform();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(.,'Category')]")));
        String validationString = driver.findElement(By.xpath("//p[contains(.,'Category')]")).getText();
        Assert.assertEquals(validationString,"Category: Women > Tops");
    }
    @Test
    public void increaseQuantityFour(){
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("4");
        driver.findElement(By.cssSelector("button[type='button']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-success close-modal btn-block']")));
        driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']")).click();
        driver.findElement(By.linkText("Cart")).click();
        String validationString = driver.findElement(By.xpath("//tr/td[4]/button")).getText();
        Assert.assertEquals(validationString,"4");
        driver.close();
    }
}
