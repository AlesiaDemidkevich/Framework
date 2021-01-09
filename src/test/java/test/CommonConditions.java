package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.TestListener;

@Listeners(TestListener.class)
public class CommonConditions {
    protected WebDriver driver;

    @BeforeMethod
    public void init(){
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void dispose(){
        DriverSingleton.closeDriver();
    }
}