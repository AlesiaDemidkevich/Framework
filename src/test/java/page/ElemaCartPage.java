package page;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import org.openqa.selenium.support.FindBy;
import service.ItemCreator;

import static util.Resolver.resolveTemplate;

public class ElemaCartPage extends AbstractPage {

    @FindBy(xpath = "//input[@class=\"basket-item-amount-filed\"]")
    WebElement cartItem;

    @FindBy(xpath = "//span[@data-entity=\"basket-item-name\"]")
    WebElement itemName;

    @FindBy(xpath = "//div[@data-column-property-code=\"SIZE\"]")
    WebElement itemSize;

    @FindBy(xpath = "//div[@data-column-property-code=\"HEIGHT\"]")
    WebElement itemHeight;

    @FindBy(xpath = "//div[@data-column-property-code=\"PRICE\"]")
    WebElement itemPrice;

    @FindBy(xpath = "//input[@class=\"basket-item-amount-filed\"]")
    WebElement itemCount;

    @FindBy(xpath = "//div[@class=\"basket-coupon-block-total-price-current\"]")
    WebElement itemCost;

    @FindBy(xpath = "//span[@class=\"basket-item-amount-btn-plus\"]")
    WebElement addOneSameProductButton;
    public ElemaCartPage(WebDriver driver) {
        super(driver);
    }

    public ElemaCartPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public Item getItem() throws InterruptedException {
        String name = itemName.getText();
        String size = itemSize.getText();
        String height = itemHeight.getText();
        double price = Double.parseDouble(itemPrice.getText());
        String count = itemCount.getAttribute("value");
        Thread.sleep(500);
        double cost = Double.parseDouble(itemCost.getText().substring(0,6).replace(",","."));

        return new Item(name,size,height,price, count, cost);
    }

    public ElemaCartPage addOneSameProduct()
    {
        waitUntilVisibilityOf(addOneSameProductButton).click();
        return this;
    }
}
