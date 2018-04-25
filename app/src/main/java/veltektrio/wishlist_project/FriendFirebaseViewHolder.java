package veltektrio.wishlist_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendFirebaseViewHolder extends RecyclerView.ViewHolder {

    public View friendBtn;
    public Context friendContext;

    public FriendFirebaseViewHolder(View btn){
        super(btn);
        friendBtn = btn;
        friendContext = btn.getContext();
    }

    public void bindFriendView(Friend friend){
        Button friendName_btn = (Button) friendBtn.findViewById(R.id.friendName_btn);
        final ArrayList<Friend> friendsList = new ArrayList<>();
        DatabaseReference databaseToFriends = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("friends");
        databaseToFriends.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    friendsList.add(snapshot.getValue(Friend.class));
                    Log.i("Friend Value: ", snapshot.getValue(Friend.class).toString());
                }
                int itemPosition = getLayoutPosition();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        friendName_btn.setText(friend.getUsername());
    }
}