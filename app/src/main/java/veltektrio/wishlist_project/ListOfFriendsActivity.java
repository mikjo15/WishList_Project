package veltektrio.wishlist_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

        //Setup FAB button & OnClickListener for it
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_friend);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("fab click to add friend");
                Intent intent_add = new Intent(getApplicationContext(), AddFriendActivity.class);
                startActivity(intent_add);
            }
        });

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


