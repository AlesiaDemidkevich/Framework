package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import static util.Resolver.resolveTemplate;

public class ElemaItemPage extends AbstractPage{

    private static final String SIZE_TEMPLATE = "li[title='%s']";
    private static final String HEIGHT_TEMPLATE = "li[title='%s']";
    private static final String COLOR_TEMPLATE = "li[title='%s']";

    @FindBy(xpath = "//a[@href=\"javascript:void(0);\"]")
    WebElement addToCartButton;

    @FindBy(xpath = "//a[@class=\"btn btn-sm btn-info\"]")
    WebElement openCartButton;

    @FindBy(xpath = "//button[@class=\"shopping-cart dropdown-toggle\"]")
    WebElement cartButton;

    @FindBy(xpath = "//h1[@class=\"name_block\"]")
    WebElement itemName;

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

    public String getProductName(){
        String name = itemName.getText();
        return name;
    }

    public ElemaItemPage scrollToItem(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        return this;
    }

    public ElemaCartPage openCart(){
        try {
            waitUntilElementIsClickable(openCartButton)
                    .click();
        }
        catch(TimeoutException e)
        {
            waitUntilElementIsClickable(cartButton)
                    .click();
            waitUntilElementIsClickable(openCartButton)
                .click();
        }
        return new ElemaCartPage(driver);
    }
}
