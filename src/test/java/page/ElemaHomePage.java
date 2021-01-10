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

    @FindBy(xpath = "//div[@class=\"api_auth_ajax auth-icon\"]")
    private WebElement authorizationButton;

    @FindBy(xpath = "//button[@class=\"api_button api_button_primary api_button_large api_button_wait api_width_1_1\"]")
    private WebElement enterButton;

    @FindBy(xpath = "//input[@name=\"LOGIN\"]")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name=\"PASSWORD\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@class=\"api_error\"]")
    private WebElement authorizationInfo;

    public ElemaHomePage(WebDriver driver) {
        super(driver);
    }

    public ElemaHomePage openPage(String url) {
        driver.get(url);
        return this;
    }

    public ElemaProductContainerPage inputProductName(String productName) {
        searchInput.click();
        waitUntilVisibilityOf(searchInput).sendKeys(productName);
        searchInput.sendKeys(Keys.ENTER);
        return new ElemaProductContainerPage(driver);
    }

    public ElemaHomePage clickAuthorizationButton() {
        waitUntilVisibilityOf(authorizationButton).click();
        return this;
    }

    public ElemaHomePage inputUserLogin(String userLogin) {
        loginField.click();
        waitUntilVisibilityOf(loginField).sendKeys(userLogin);
        return this;
    }

    public ElemaHomePage inputUserPassword(String userPassword) {
        passwordField.click();
        waitUntilVisibilityOf(passwordField).sendKeys(userPassword);
        return this;
    }

    public ElemaHomePage clickEnterButton() {
        waitUntilVisibilityOf(enterButton).click();
        return this;
    }
    public String getNoCorrectInfo(){
        return  waitUntilVisibilityOf(authorizationInfo).getText();
    }
}
