package service;

import model.Item;
import static util.Resolver.resolveTemplate;


public class ItemCreator {
    public static final String ITEM_URL="test.data.item.%s.url";
    public static final String ITEM_NAME = "test.data.item.%s.name";
    public static final String ITEM_SIZE = "test.data.item.%s.size";
    public static final String ITEM_HEIGHT = "test.data.item.%s.height";
    public static final String ITEM_PRICE = "test.data.item.%s.price";


    public static Item withCredentialsFromProperty(String orderNumber){
        orderNumber = orderNumber.toLowerCase();

        return new Item(TestDataReader.getTestData(resolveTemplate(ITEM_URL, orderNumber)), TestDataReader.getTestData(resolveTemplate(ITEM_NAME, orderNumber)),
                TestDataReader.getTestData(resolveTemplate(ITEM_SIZE, orderNumber)), TestDataReader.getTestData(resolveTemplate(ITEM_HEIGHT, orderNumber)),
                Double.parseDouble(TestDataReader.getTestData(resolveTemplate(ITEM_PRICE, orderNumber))));
    }

}