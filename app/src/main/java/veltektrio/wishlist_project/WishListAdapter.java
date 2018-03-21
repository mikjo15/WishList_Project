package veltektrio.wishlist_project;

import android.support.design.widget.Snackbar;
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
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recycleview, parent, false);
        ViewHolder vh = new ViewHolder(tv);
        return vh;
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
        holder.textView.setText(numbers.get(position).toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(holder.getAdapterPosition(),v);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.i("Holder",String.format("Getting item count: %s",numbers.size()));
        return numbers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public ViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    /**
     * Deletes item at position
     * Creates a snackbar with the option to undo deletion
     * @param position
     * @param v View used for snackbar to find go back hierarychally and find coordinator layout
     */
    public void deleteItem(final int position, View v){
        String message = String.format("Removing item at position: %s with value: %s",position,numbers.get(position));
        Log.i("Holder", message);
        final int number = numbers.get(position);
        Snackbar snack = Snackbar.make(v,message, LENGTH_LONG);
        snack.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.add(position,number);
                notifyDataSetChanged();
            }
        });
        snack.show();
        numbers.remove(position);
        notifyDataSetChanged();
    }
}
