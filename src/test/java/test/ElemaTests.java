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
//               .openPage("https://elema.by/catalog/zhenskie-uteplennye-palto/palto-zhenskoe-demisezonnoe-1-10509-1/")
//               .scrollToItem()
//               .chooseSize("44")
//               .chooseHeight("170")
//               .addToCart()
//               .openCart()
//               .getItem();
                Assert.assertTrue(item.equals(expectedItem));
                Assert.assertEquals(item.getItemCount(), "1");
    }

//    @Test
//    public void addThreeItemsToCartTest(){
//        Item expectedItem = ItemCreator.withCredentialsFromProperty();
//
//        Item item = new ElemaItemPage(driver)
//                .openPage(expectedItem.getItemUrl())
//                .scrollToItem()
//                .chooseSize(expectedItem.getItemSize())
//                .chooseHeight(expectedItem.getItemHeight())
//                .addToCart()
//                .scrollToItem()
//                .addToCart()
//                .scrollToItem()
//                .addToCart()
//                .openCart()
//                .getItem();
//
//         Assert.assertEquals(item.getItemCount(), "3");
//    }

    @AfterMethod
    public void closeDriver(){
       driver.close();
    }
}
