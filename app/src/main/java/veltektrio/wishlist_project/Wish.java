package veltektrio.wishlist_project;

/**
 * Created by CathrineBuch on 14-03-2018.
 */

public class Wish {
    public Wish(String n, String s, String u, double p, String no, String c, String sh){
    name = n;
    size = s;
    url = u;
    price = p;
    note = no;
    color = c;
    shop = sh;
    }

    public Wish(){}

    public String getName(){return name;}
    public void setName(String n) {name = n;}
    public String getItemSize(){return size;}
    public void setItemSize(String n) {size = n;}
    public String getUrl(){return url;}
    public void setUrl(String n) {url = n;}
    public double getPrice(){return price;}
    public void setPrice(double n) {price = n;}
    public String getNote(){return note;}
    public void setNote(String n) {note = n;}
    public String getColor(){return color;}
    public void setCOlor(String n) {color = n;}
    public String getShop(){return shop;}
    public void setShop(String n) {shop = n;}


    private String name = "NO NAME";
    private String size = "NO SIZE";
    private String url = "";
    private double price = 0.0;
    private String note = "NO NOTE";
    private String color = "NO COLOR";
    private String shop = "NO SHOP";
}
