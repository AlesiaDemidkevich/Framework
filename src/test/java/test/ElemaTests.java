package test;

import jdk.nashorn.internal.runtime.Debug;
import model.Item;
import model.User;
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
        Item expectedItem = ItemCreator.withCredentialsFromProperty();

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
    public void addThreeItemsToCartTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty();

        Item item = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .scrollToItem()
                .addToCart()
                .scrollToItem()
                .addToCart()
                .openCart()
                .getItem();

        double cost = item.getItemPrice();
        double expectedCost = expectedItem.getItemPrice()*3;

        Assert.assertTrue(item.equals(expectedItem));
        Assert.assertEquals(item.getItemCount(), "3");
        Assert.assertEquals(cost,expectedCost);
    }

    @Test
    public void searchTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty();

        String item = new ElemaHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .inputProductName(expectedItem.getItemName())
                .chooseTargetModel()
                .getProductName();

        Assert.assertTrue(item.equals(expectedItem.getItemName()));

    }

    @Test
    public void changeAmountOfProductTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty();

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
        Item expectedItem = ItemCreator.withCredentialsFromProperty();

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
        Item expectedItem = ItemCreator.withCredentialsFromProperty();

        ElemaCartPage cartPage = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openCart()
                .putPromoCode("qwerty");

        Assert.assertEquals(cartPage.getPromoCodeInfo(),"qwerty - купон не найден");
    }

    @Test
    public void checkInvalidPasswordTest()
    {
        User user = UserCreator.withCredentialsFromProperty();

        ElemaHomePage authorizationInfo = new ElemaHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .clickAuthorizationButton()
                .inputUserLogin(user.getUserLogin())
                .inputUserPassword(user.getUserPassword())
                .clickEnterButton();

        Assert.assertEquals(authorizationInfo.getNoCorrectInfo(),"Неверный логин или пароль.");

    }

    @AfterMethod
    public void closeDriver(){
       driver.close();
    }
}
