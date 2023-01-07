package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.inaracademy.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initialiseDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Recources\\GlobalData.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //firefox
            System.setProperty("webdriver.firefox.driver","C:\\Users\\idris\\Desktop\\drivers\\firefox.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            //edge
            System.setProperty("webdriver.edge.driver","C:\\Users\\idris\\Desktop\\drivers\\edge.exe");
            driver = new EdgeDriver();
        }

        return driver;
    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver = initialiseDriver();
        landingPage  = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
