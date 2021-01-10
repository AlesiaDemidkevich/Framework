package page;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import org.openqa.selenium.support.FindBy;
import service.ItemCreator;
import org.openqa.selenium.TimeoutException;

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

    @FindBy(xpath = "//input[@class=\"basket-item-amount-filed\"]")
    WebElement itemCount;

    @FindBy(xpath = "//span[@class=\"basket-item-amount-btn-plus\"]")
    WebElement addOneSameProductButton;

    @FindBy(xpath = "//span[@class=\"basket-item-actions-remove d-block d-sm-none\"]")
    WebElement removeProductButton;

    @FindBy(xpath = "//div[@class=\"basket-coupon-block-total-price-current\"]")
    WebElement itemPrice;

    By productList = By.xpath("//tr[@class=\"basket-items-list-item-container\"]");
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
        String count = itemCount.getAttribute("value");
        Thread.sleep(500);
        double price = Double.parseDouble(itemPrice.getText().substring(0,itemPrice.getText().indexOf(" ")).replace(",","."));

        return new Item(name,size,height,price, count);
    }

    public ElemaCartPage addOneSameProduct()
    {
        waitUntilVisibilityOf(addOneSameProductButton).click();
        return this;
    }

    public ElemaCartPage removeFromCart()
    {
        waitUntilVisibilityOf(removeProductButton).click();
        return this;
    }

    public boolean isEmpty() {

        try {
            waitUntilInvisibilityOf(productList);
            return true;

        } catch(TimeoutException e) {
            return false;
        }
    }
}
