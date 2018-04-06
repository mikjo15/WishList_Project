package veltektrio.wishlist_project;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Mikkel on 19-03-2018.
 */

public class mFriends extends RecyclerView.Adapter<mFriends.ViewHolder> {

    private List<Wishlist> wishList;
    private Context context;

    public mFriends(List<Wishlist> wishList, Context context) {
        this.wishList = wishList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_lists_btn, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Wishlist currentList = wishList.get(position);
        final String user_name = currentList.getUser_name();

        holder.friend_btn.setText(user_name);
        holder.friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("database", user_name);
                Fragment fragment_recycle = new ItemListFragment();
                fragment_recycle.setArguments(bundle);
                ((ListOfFriendsActivity)context).getSupportFragmentManager().beginTransaction().add(R.id.MyWishList_ItemListFragment, fragment_recycle).commit();
            }
        });
    }

    @Override
    public int getItemCount() { return wishList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button friend_btn;

        public ViewHolder(View itemView) {
            super(itemView);

            friend_btn = itemView.findViewById(R.id.friends_btn);
        }
    }
}
