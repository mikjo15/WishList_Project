package veltektrio.wishlist_project;

import java.util.Vector;

/**
 * Created by CathrineBuch on 14-03-2018.
 */

public class Wishlist extends Wish {

    public Wishlist(String user_name){ this.user_name = user_name; }

    public void remove_wish(Wish w){
        wishlist.remove(w);
    }
    public void add_wish(Wish w){
        wishlist.addElement(w);
    }
    public int getSize() { return wishlist.size(); }
    public Wish getPosition(int position) {
        return wishlist.get(position);
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    private Vector<Wish> wishlist;

    private String user_name;
}


