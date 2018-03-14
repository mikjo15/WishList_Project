package veltektrio.wishlist_project;

/**
 * Created by CathrineBuch on 14-03-2018.
 */

public class Wish {
    public Wish(String n, category cat, String s, String u, double p, String no, String c, String sh){
    name = n;
    cat_id = cat;
    size = s;
    url = u;
    price = p;
    note = no;
    color = c;
    shop = sh;
    }

    public Wish(){}


    private String name = "NO NAME";
    private enum category{clothes, outdoor, game, book, kitchen, electronic, other};
    private category cat_id;
    private String size = "NO SIZE";
    private String url = "";
    private double price = 0.0;
    private String note = "NO NOTE";
    private String color = "NO COLOR";
    private String shop = "NO SHOP";
}
