package model;

import java.util.Objects;

public class Item {
    private String url;
    private String name;
    private String size;
    private String height;
    private double price;
    private double cost;
    private String count;


    public Item(String url, String name, String size, String height, double price) {
        this.url = url;
        this.name = name;
        this.size = size;
        this.height= height;
        this.price = price;
       }

    public Item(String name, String size, String height, double price, String count) {
        this.name = name;
        this.size = size;
        this.height= height;
        this.price = price;
        this.count = count;
    }

    public Item(String name, String size, String height, double price, String count, double cost) {
        this.name = name;
        this.size = size;
        this.height= height;
        this.price = price;
        this.count = count;
        this.cost = cost;
    }

    public String getItemUrl() {
        return url;
    }

    public void setItemUrl(String url) {
        this.url = url;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public String getItemSize() {
        return size;
    }

    public void setItemSize(String size) {
        this.size = size;
    }

    public String getItemHeight() {
        return height;
    }

    public void setItemHeight(String height) {
        this.height = height;
    }

    public double getItemPrice() {
        return price;
    }

    public void setItemPrice(double price) {
        this.price = price;
    }

    public String getItemCount() {
        return count;
    }

    public void setItemCount(String count) {
        this.count = count;
    }

    public double getItemCost() {
        return cost;
    }

    public void setItemCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "  url ='" + url + '\'' +
                "  name ='" + name + '\'' +
                ", size ='" + size + '\'' +
                ", height ='" + height + '\'' +
                ", price ='" + price + '\'' +
                ", count ='" + count + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return  Objects.equals(getItemName(), item.getItemName()) &&
                Objects.equals(getItemSize(),item.getItemSize()) &&
                Objects.equals(getItemHeight(),item.getItemHeight()) &&
                Objects.equals(getItemPrice(),item.getItemPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemName(), getItemSize(), getItemHeight(), getItemPrice());
    }
}