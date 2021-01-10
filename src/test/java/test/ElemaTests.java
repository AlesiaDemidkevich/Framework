package test;

import model.Item;
import org.apache.commons.math3.util.Precision;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ElemaHomePage;
import page.ElemaItemPage;
import page.ElemaCartPage;
import service.ItemCreator;

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
    public void increaseCountOfProductTest() throws InterruptedException {
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
    public void removeItemFromCartTest() {
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
    public void checkPromoCodeTest() {
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
    public void checkTotalPriceTest() {
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
        double expectedPrice = Precision.round(costOfFirsItem + costOfSecondItem,2) ;

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

    @Test
    public void decreaseCountOfProductTest() throws InterruptedException {
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");

        Item item = new ElemaItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .scrollToItem()
                .chooseSize(expectedItem.getItemSize())
                .chooseHeight(expectedItem.getItemHeight())
                .addToCart()
                .openCart()
                .addOneSameProduct()
                .removeOneSameProduct()
                .getItem();

        double cost = item.getItemPrice();
        double expectedCost = expectedItem.getItemPrice();

        Assert.assertTrue(item.equals(expectedItem));
        Assert.assertEquals(item.getItemCount(), "1");
        Assert.assertEquals(cost,expectedCost);
    }
}
