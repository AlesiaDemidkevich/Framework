package page;

import model.Item;
import org.openqa.selenium.By;
import service.ItemCreator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static util.Resolver.resolveTemplate;

public class ElemaItemPage extends AbstractPage{

    private static final String SIZE_TEMPLATE = "li[title='%s']";
    private static final String HEIGHT_TEMPLATE = "li[title='%s']";
    private static final String COLOR_TEMPLATE = "li[title='%s']";

    @FindBy(xpath = "//a[@href=\"javascript:void(0);\"]")
    WebElement addToCartButton;

    @FindBy(xpath = "//a[@href=\"/personal/cart/\"]")
    WebElement openCartButton;

    public ElemaItemPage(WebDriver driver) {
        super(driver);
    }

    public ElemaItemPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public ElemaItemPage chooseSize(String size){
        waitUntilElementIsClickable(By.cssSelector(resolveTemplate(SIZE_TEMPLATE,size))).click();
        return this;
    }

    public ElemaItemPage chooseHeight(String height){
        waitUntilElementIsClickable(By.cssSelector(resolveTemplate(HEIGHT_TEMPLATE,height))).click();
        return this;
    }

    public ElemaItemPage addToCart(){
        waitUntilElementIsClickable(addToCartButton)
                .click();
        return this;
    }

    public ElemaItemPage scrollToItem(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        return this;
    }

    public ElemaCartPage openCart(){
        waitUntilElementIsClickable(openCartButton)
                .click();
        return new ElemaCartPage(driver);
    }

}
