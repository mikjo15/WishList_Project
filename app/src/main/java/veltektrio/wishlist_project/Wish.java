package veltektrio.wishlist_project;

/**
 * Created by CathrineBuch on 14-03-2018.
 */

public class Wish {
    public Wish(String n, String s, String u, double p,  String c, String sh, String no){
    name = n;
    size = s;
    url = u;
    price = p;
    color = c;
    shop = sh;
    note = no;
    }

    public Wish(){}


    private String name = "NO NAME";
    private enum category{clothes, outdoor, game, book, kitchen, electronic, other};
    private category cat_id;
    private String size = "NO SIZE";
    private String url = "";
    private double price = 0.0;
    private String color = "NO COLOR";
    private String shop = "NO SHOP";
    private String note = "NO NOTE";
}
