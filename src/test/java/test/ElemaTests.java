package test;

import jdk.nashorn.internal.runtime.Debug;
import model.Item;
import model.User;
import org.apache.commons.math3.util.Precision;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ElemaHomePage;
import page.ElemaProductContainerPage;
import page.ElemaItemPage;
import page.ElemaCartPage;
import service.ItemCreator;
import service.UserCreator;
import service.TestDataReader;

public class ElemaTests extends CommonConditions{
    private static final String HOMEPAGE_URL = "https://elema.by/";

    @Test
    public void addItemToCartTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

       Item item = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openCart()
                .getItem();

                Assert.assertTrue(item.equals(expectedItem));
                Assert.assertEquals(item.getItemCount(), "1");
    }

    @Test
    public void addTwoItemsToCartTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

        Item item = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openCart()
                .getItem();

        double cost = item.getItemPrice();
        double expectedCost = expectedItem.getItemPrice()*2;

        Assert.assertTrue(item.equals(expectedItem));
        Assert.assertEquals(item.getItemCount(), "2");
        Assert.assertEquals(cost, expectedCost);
    }

    @Test
    public void searchTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

        String item = new ElemaHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .inputProductName(expectedItem.getItemName())
                .chooseTargetModel()
                .getProductName();

        Assert.assertTrue(item.equals(expectedItem.getItemName()));

    }

    @Test
    public void changeAmountOfProductTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

        Item item = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openCart()
                .addOneSameProduct()
                .getItem();

        double cost = item.getItemPrice();
        double expectedCost = expectedItem.getItemPrice()*2;

        Assert.assertTrue(item.equals(expectedItem));
        Assert.assertEquals(item.getItemCount(), "2");
        Assert.assertEquals(cost,expectedCost);
    }

    @Test
    public void removeItemFromCartTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

        boolean isCartEmpty = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openCart()
                .removeFromCart()
                .isEmpty();

        Assert.assertTrue(isCartEmpty);
    }

    @Test
    public void checkPromoCodeTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

        ElemaCartPage cartPage = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openCart()
                .scrollToItem()
                .putPromoCode("qwerty");

        Assert.assertEquals(cartPage.getPromoCodeInfo(),"qwerty - купон не найден");
    }

    @Test
    public void checkInvalidPasswordTest()
    {
        User user = UserCreator.withCredentialsFromProperty("first");

        ElemaHomePage authorizationInfo = new ElemaHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .clickAuthorizationButton()
                .inputUserLogin(user.getUserLogin())
                .inputUserPassword(user.getUserPassword())
                .clickEnterButton();

        Assert.assertEquals(authorizationInfo.getNoCorrectInfo(),"Неверный логин или пароль.");

    }

    @Test
    public void checkTotalPriceTest() throws InterruptedException {
        Item firstExpectedItem = ItemCreator.withCredentialsFromProperty("first");
        Item secondExpectedItem = ItemCreator.withCredentialsFromProperty("second");

        ElemaCartPage cartPage = new ElemaItemPage(driver)
                .openPage(firstExpectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(firstExpectedItem.getItemSize())
                .chooseHeight(firstExpectedItem.getItemHeight())
                .addToCart()
                .openPage(secondExpectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(secondExpectedItem.getItemSize())
                .chooseHeight(secondExpectedItem.getItemHeight())
                .addToCart()
                .openCart();

        double totalPrice = cartPage.getTotalPrice();
        double costOfFirsItem = firstExpectedItem.getItemPrice();
        double costOfSecondItem = secondExpectedItem.getItemPrice();
        double expectedPrice = costOfFirsItem + costOfSecondItem ;

        Assert.assertEquals(totalPrice, expectedPrice);
    }

    @Test
    public void checkSaleTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty("third");

        Item item = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openCart()
                .getItem();

        double totalPrice = item.getItemPrice();
        double expectedPrice = Precision.round(expectedItem.getItemPrice()*0.7,2);

        Assert.assertEquals(item.getItemCount(), "1");
        Assert.assertEquals(totalPrice, expectedPrice);
    }

    @AfterMethod
    public void closeDriver(){
       driver.close();
    }
}
