package page;

import model.Item;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ElemaCartPage extends AbstractPage {

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

    @FindBy(xpath = "//span[@class=\"basket-item-amount-btn-minus\"]")
    WebElement removeOneSameProductButton;

    @FindBy(xpath = "//div[@class=\"basket-coupon-block-total-price-current\"]")
    WebElement itemPrice;

    @FindBy(xpath = "//*[@id=\"basket-root\"]/div[3]/div/div/div[1]/div/div[2]/div/input")
    WebElement promoCodeInput;

    @FindBy(xpath = "//span[@class=\"basket-coupon-text\"]")
    WebElement promoCodeInfo;

    By productList = By.xpath("//tr[@class=\"basket-items-list-item-container\"]");

    public ElemaCartPage(WebDriver driver) {
        super(driver);
    }

    public ElemaCartPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public Item getItem() throws InterruptedException {
        Thread.sleep(500);
        String name = itemName.getText();
        String size = itemSize.getText();
        String height = itemHeight.getText();
        String count = itemCount.getAttribute("value");
        double price = Double.parseDouble(itemPrice.getText().substring(0,itemPrice.getText().indexOf("р")-1).replace(",",".").replace(" ",""));

        return new Item(name,size,height,price, count);
    }

    public ElemaCartPage addOneSameProduct() {
        waitUntilVisibilityOf(addOneSameProductButton).click();
        return this;
    }

    public ElemaCartPage removeOneSameProduct() {
        waitUntilElementIsClickable(removeOneSameProductButton).click();
        return this;
    }

    public ElemaCartPage removeFromCart() {
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

    public ElemaCartPage putPromoCode(String promoCode) {
        waitUntilVisibilityOf(promoCodeInput).click();
        waitUntilVisibilityOf(promoCodeInput).sendKeys(promoCode);
        promoCodeInput.sendKeys(Keys.ENTER);
        return this;
    }

    public String getPromoCodeInfo() {
        return waitUntilVisibilityOf(promoCodeInfo).getText();
    }

    public ElemaCartPage scrollToItem() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", promoCodeInput);
        return this;
    }

    public double getTotalPrice() {
        double price = Double.parseDouble(itemPrice.getText().substring(0,itemPrice.getText().indexOf("р")-1).replace(",",".").replace(" ",""));
        return price;
    }
}
