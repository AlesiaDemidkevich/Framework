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

    @Test
    public void addItemToCartTest(){
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

    /*@Test
    public void addThreeItemsToCartTest(){
        String countOfItems = new ElemaHomePage(driver)
                .openPage()
                .inputProductNumber(productNumber)
                .chooseTargetModel()
                .scrollToItem()
                .chooseSize()
                .chooseHeight()
                .addToCart()
                .addToCart()
                .addToCart()
                .openCart()
                .getCountOfItems();

        Assert.assertEquals(countOfItems, "3");
    }*/

    @AfterMethod
    public void closeDriver(){
       driver.close();
    }
}
