package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ElemaProductContainerPage extends AbstractPage{

    @FindBy(xpath = "//div[@class=\"col-xs-12 col-sm-6 col-md-6\"]")
    WebElement targetModel;

    public ElemaProductContainerPage(WebDriver driver) {
        super(driver);
    }

    public ElemaProductContainerPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public ElemaItemPage chooseTargetModel() {
        targetModel.click();
        return new ElemaItemPage(driver);
    }
}
