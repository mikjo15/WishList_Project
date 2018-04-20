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

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by Mikkel on 19-03-2018.
 */

public class mFriends extends RecyclerView.Adapter<mFriends.ViewHolder> {

    private List<Friend> listOfFriends;
    private Context context;

    public mFriends(List<Friend> listOfFriends, Context context) {
        this.listOfFriends = listOfFriends;
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
        Friend currentFriend = listOfFriends.get(position);

        holder.friend_btn.setText(currentFriend.username);
      /*  holder.friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("database", FirebaseAuth.getInstance().getCurrentUser());
                Fragment fragment_recycle = new ItemListFragment();
                fragment_recycle.setArguments(bundle);
                ((ListOfFriendsActivity)context).getSupportFragmentManager().beginTransaction().add(R.id.MyWishList_ItemListFragment, fragment_recycle).commit();
            }
        });*/
    }

    @Override
    public int getItemCount() { return listOfFriends.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button friend_btn;

        public ViewHolder(View view) {
            super(view);

            friend_btn = view.findViewById(R.id.friends_btn);
        }
    }
}
