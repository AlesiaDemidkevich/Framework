package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton(){

    }

    public static WebDriver getDriver() {
        if (driver == null) {
          switch (System.getProperty("browser")) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                   driver = new EdgeDriver();
                   break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}