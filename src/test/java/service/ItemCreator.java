package service;

import model.Item;

public class ItemCreator {
    public static final String ITEM_URL="test.data.item.url";
    public static final String ITEM_NAME = "test.data.item.name";
    public static final String ITEM_SIZE = "test.data.item.size";
    public static final String ITEM_HEIGHT = "test.data.item.height";
    public static final String ITEM_PRICE = "test.data.item.price";


    public static Item withCredentialsFromProperty(){
        return new Item(TestDataReader.getTestData(ITEM_URL), TestDataReader.getTestData(ITEM_NAME),
                TestDataReader.getTestData(ITEM_SIZE), TestDataReader.getTestData(ITEM_HEIGHT),
                Double.parseDouble(TestDataReader.getTestData(ITEM_PRICE)));
    }

}