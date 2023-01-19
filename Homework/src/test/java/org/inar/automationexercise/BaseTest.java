package org.inar.automationexercise;

import org.apache.commons.io.FileUtils;
import org.inar.utilities.Pages;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.inar.utilities.Driver;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected Pages pages = new Pages();

    public String getScreenShot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png";
    }
}
