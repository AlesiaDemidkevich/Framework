package test;

import jdk.nashorn.internal.runtime.Debug;
import model.Item;
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
        Assert.assertTrue(item.equals(expectedItem));
         Assert.assertEquals(item.getItemCount(), "3");
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
        System.out.println(cost);

        double totalCost = expectedItem.getItemPrice()*2;
        System.out.println(totalCost);

        Assert.assertTrue(item.equals(expectedItem));
        Assert.assertEquals(cost,totalCost);
    }


    @AfterMethod
    public void closeDriver(){
       driver.close();
    }
}
