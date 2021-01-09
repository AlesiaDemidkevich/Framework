package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElemaHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://elema.by/";


    @FindBy(xpath = "//*[@id=\"main-nav\"]/div/form/div/input")
    private WebElement searchInput;

    public ElemaHomePage(WebDriver driver) {
        super(driver);
    }

    public ElemaHomePage openPage(String url) {
        driver.get(url);
        return this;
    }

    public ElemaProductContainerPage inputProductNumber(String productNumber) {
        searchInput.click();
        waitUntilVisibilityOf(searchInput).sendKeys(productNumber);
        searchInput.sendKeys(Keys.ENTER);
        return new ElemaProductContainerPage(driver);
    }
}
