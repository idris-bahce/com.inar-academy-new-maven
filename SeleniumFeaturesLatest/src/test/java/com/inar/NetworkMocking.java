package com.inar;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.fetch.Fetch;

import java.util.Optional;

public class NetworkMocking {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\idris\\Desktop\\drivers\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Fetch.enable(Optional.empty(),Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused ->
        {
            if (requestPaused.getRequest().getUrl().contains("shetty")){
                String mockedUrl = requestPaused.getRequest().getUrl().replace("=shetty","=BadGuy");
                System.out.println(mockedUrl);

                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(mockedUrl), Optional.of(requestPaused.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(),Optional.empty()));
            }else {
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(requestPaused.getRequest().getUrl()), Optional.of(requestPaused.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(),Optional.empty()));
            }
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
    }
}
