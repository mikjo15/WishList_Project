package veltektrio.wishlist_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;

/* Bruges som viewholder til friends list. (knapperne hvorpå der står navne på venner)
 */
public class FriendViewHolder extends RecyclerView.ViewHolder  { //implements View.OnClickListener

    public Button friendview;

    public FriendViewHolder(View v) {
        super(v);

        friendview = v.findViewById(R.id.friends_btn);
        //mView.setOnClickListener(this);
    }


    public void bindFriendsButton(Friend friend) {
        friendview.setText(friend.username);
    }
}
