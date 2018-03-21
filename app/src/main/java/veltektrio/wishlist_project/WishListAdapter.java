package veltektrio.wishlist_project;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

/**
 * Created by hgdru on 21-03-2018.
 */

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    Wishlist currentList;

    public WishListAdapter(Wishlist currentlist) {
        this.currentList = currentlist;
    }

    /**
     * Creates the viewholder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_recycleview, parent, false);
        return new ViewHolder(v);
    }


    /**
     * Binds current viewholder, and creates on click listener for each item (NOT the best design, regarding click listeners)
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final WishListAdapter.ViewHolder holder, int position) {
        final String message = String.format("Setting textview for position: %s" , position);
        Log.i("Holder",message);
        Wish currentwish = currentList.getWish(position);
        holder.input_name.setText(currentwish.getName());
        holder.input_size.setText(currentwish.getItemSize());
        holder.input_url.setText(currentwish.getUrl());
        holder.input_price.setText(Double.toString(currentwish.getPrice()));
        holder.input_note.setText(currentwish.getNote());
        holder.input_color.setText(currentwish.getColor());
        holder.input_shop.setText(currentwish.getShop());
    }

    @Override
    public int getItemCount() { return currentList.getSize();    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView input_name;
        public TextView input_size;
        public TextView input_url;
        public TextView input_price;
        public TextView input_note;
        public TextView input_color;
        public TextView input_shop;

        public ViewHolder(View v) {
            super(v);
            input_name = v.findViewById(R.id.InputText_name);
            input_size = v.findViewById(R.id.InputText_size);
            input_url = v.findViewById(R.id.InputText_link);
            input_price = v.findViewById(R.id.InputText_price);
            input_note = v.findViewById(R.id.InputText_note);
            input_color = v.findViewById(R.id.InputText_color);
            input_shop = v.findViewById(R.id.InputText_shop);
        }
    }

    //Tilføj update funktion. Denne skal bruges når der tilføjes eller redigeres ønsker
    //Opret unikt ID på alle vores ønsker
}
