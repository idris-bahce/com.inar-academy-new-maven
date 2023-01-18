package com.inar;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.network.Network;
import org.openqa.selenium.devtools.v108.network.model.ConnectionType;

import java.util.Optional;

public class NetworkSpeed {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\idris\\Desktop\\drivers\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

       devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
       devTools.send(Network.emulateNetworkConditions(false,3000,20000,
               100000, Optional.of(ConnectionType.ETHERNET)));
       devTools.addListener(Network.loadingFailed(),loadingFailed ->
       {
           System.out.println(loadingFailed.getErrorText());
           System.out.println(loadingFailed.getTimestamp());
       });

        long starTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-starTime);//15159  1545
    }
}
