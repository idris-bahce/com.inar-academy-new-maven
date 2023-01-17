package com.inar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SetGeoLocation {//localization testing

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\idris\\Desktop\\drivers\\chromedriver.exe");
        //52.53845254244024, 13.375444366563492
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude",52);
        coordinates.put("longitude",60);
        coordinates.put("accuracy",1);

        driver.executeCdpCommand("Emulation.setGeolocationOverride",coordinates);
        //Does not work for google and netflix

//        driver.get("http://google.com");
//        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
//
//        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
//        System.out.println(driver.findElement(By.cssSelector("h1[data-uia='hero-title']")).getText());
        driver.get("https://my-location.org/");
        driver.manage().window().maximize();

        System.out.println(driver.findElement(By.id("address")).getText());
    }
}
