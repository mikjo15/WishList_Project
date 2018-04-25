package veltektrio.wishlist_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOfFriendsActivity extends AppCompatActivity {
    private DatabaseReference databaseToFriends;
    private FirebaseRecyclerAdapter adapterToFriendBtn;


    @BindView(R.id.listOfFriends_RecyclerView)
    RecyclerView listOfFriends_RecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_friends);
        ButterKnife.bind(this);

        databaseToFriends = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("friends");
        setUpFirebaseAdapter();
    }

    public void setUpFirebaseAdapter(){
        Log.i("TEST!!! ", "inside");
        adapterToFriendBtn = new FirebaseRecyclerAdapter<Friend, FriendFirebaseViewHolder>
                (Friend.class, R.layout.friends_lists_btn, FriendFirebaseViewHolder.class,
                        databaseToFriends) {
            @Override
            protected void populateViewHolder(FriendFirebaseViewHolder viewHolder, Friend model, int position) {
                viewHolder.bindFriendView(model);

                /*viewHolder.friendBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("clicked", "Friend");
                        String refToWish = getRef(position).toString();
                        Intent intent = new Intent(getContext(), AddWishActivity.class);
                        intent.putExtra("refToWish", refToWish);
                        startActivity(intent);
                    }
                });*/
            }
        };

        listOfFriends_RecyclerView.setHasFixedSize(true);
        listOfFriends_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfFriends_RecyclerView.setAdapter(adapterToFriendBtn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapterToFriendBtn.cleanup();
    }



}


