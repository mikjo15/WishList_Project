package veltektrio.wishlist_project;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by CathrineBuch on 14-03-2018.
 */

public class Wishlist extends Wish {

    public Wishlist(String un){
        user_name = un;
        wishlist = new ArrayList<>();
    }

    public void remove_wish(Wish w){
        wishlist.remove(w);
    }
    public void add_wish(Wish w){
       wishlist.add(w);
    }
    public int getSize() { return wishlist.size(); }

    public Wish getPosition(int position) {
        return wishlist.get(position);
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String un) {
        user_name = un;
    }

    public Wish getWish(int p) { return wishlist.get(p);}

    //private Vector<Wish> wishlist ;
    private ArrayList<Wish> wishlist;

    private String user_name;
}


