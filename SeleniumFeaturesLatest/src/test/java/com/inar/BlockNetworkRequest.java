package com.inar;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.network.Network;

import java.util.Optional;

public class BlockNetworkRequest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\idris\\Desktop\\drivers\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));

        long starTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("a[role='button']")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector("button[class='add-to-cart btn btn-default']")).click();
        System.out.println(driver.findElement(By.cssSelector("p[class='sp']")).getText());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-starTime);//1766  1965
    }
}
