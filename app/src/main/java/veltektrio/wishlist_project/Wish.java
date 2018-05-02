package veltektrio.wishlist_project;

/**
 * Created by CathrineBuch on 14-03-2018.
 */

public class Wish {

    public Wish(String name, String itemSize, String url, String price, String shop, String color, String note) {
        this.name = name;
        this.itemSize = itemSize;
        this.url = url;
        this.price = price;
        this.shop = shop;
        this.color = color;
        this.note = note;
    }

    public Wish() {
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String size) {
        this.itemSize = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String name;
    private String itemSize;
    private String url;
    private String price;
    private String shop;
    private String color;
    private String note;
}
