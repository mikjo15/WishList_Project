package veltektrio.wishlist_project;

import java.util.Vector;

/**
 * Created by CathrineBuch on 14-03-2018.
 */

public class Wishlist extends Wish {

    public Wishlist(){}

    public void remove_wish(Wish w){
        wishlist.remove(w);
    }
    public void add_wish(Wish w){
        wishlist.addElement(w);
    }

    private Vector<Wish> wishlist;
}


