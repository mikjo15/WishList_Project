package veltektrio.wishlist_project;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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


        if(currentwish.getName() != "")
            holder.input_name.setText(currentwish.getName());
        else {
            holder.input_name.setVisibility(View.GONE);
            holder.text_name.setVisibility(View.GONE);
        }

        if(currentwish.getItemSize() != "")
            holder.input_itemSize.setText(currentwish.getItemSize());
        else {
            holder.input_itemSize.setVisibility(View.GONE);
            holder.text_itemSize.setVisibility(View.GONE);
        }

        if(currentwish.getUrl() != "")
            holder.input_url.setText(currentwish.getUrl());
        else {
            holder.input_url.setVisibility(View.GONE);
            holder.text_url.setVisibility(View.GONE);
        }

        if(currentwish.getPrice() != 0)
            holder.input_price.setText(Double.toString(currentwish.getPrice()));
        else {
            holder.input_price.setVisibility(View.GONE);
            holder.text_price.setVisibility(View.GONE);
        }

        if(currentwish.getNote() != "")
            holder.input_note.setText(currentwish.getNote());
        else {
            holder.input_note.setVisibility(View.GONE);
            holder.text_note.setVisibility(View.GONE);
        }

        if(currentwish.getColor() != "")
            holder.input_color.setText(currentwish.getColor());
        else {
            holder.input_color.setVisibility(View.GONE);
            holder.text_color.setVisibility(View.GONE);
        }

        if(currentwish.getShop() != "")
            holder.input_shop.setText(currentwish.getShop());
        else {
            holder.input_shop.setVisibility(View.GONE);
            holder.text_shop.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() { return currentList.getSize();    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView input_name;
        public TextView input_itemSize;
        public TextView input_url;
        public TextView input_price;
        public TextView input_note;
        public TextView input_color;
        public TextView input_shop;

        public TextView text_name;
        public TextView text_itemSize;
        public TextView text_url;
        public TextView text_price;
        public TextView text_note;
        public TextView text_color;
        public TextView text_shop;

        public ViewHolder(View v) {
            super(v);
            input_name = v.findViewById(R.id.InputText_name);
            input_itemSize = v.findViewById(R.id.InputText_size);
            input_url = v.findViewById(R.id.InputText_link);
            input_price = v.findViewById(R.id.InputText_price);
            input_note = v.findViewById(R.id.InputText_note);
            input_color = v.findViewById(R.id.InputText_color);
            input_shop = v.findViewById(R.id.InputText_shop);


            text_name = v.findViewById(R.id.textView_name);
            text_itemSize = v.findViewById(R.id.textView_size);
            text_url = v.findViewById(R.id.textView_link);
            text_price = v.findViewById(R.id.textView_price);
            text_note = v.findViewById(R.id.textView_note);
            text_color = v.findViewById(R.id.textView_color);
            text_shop = v.findViewById(R.id.textView_shop);
        }
    }

    //Tilføj update funktion. Denne skal bruges når der tilføjes eller redigeres ønsker
    //Opret unikt ID på alle vores ønsker
    public void HideEmptyTextboxes(Wish CurrentWish) {

    }
}
