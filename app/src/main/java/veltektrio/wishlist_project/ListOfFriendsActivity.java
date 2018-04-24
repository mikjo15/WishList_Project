package veltektrio.wishlist_project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOfFriendsActivity extends AppCompatActivity {

    @BindView(R.id.friends_RecyclerView)
    public RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_friends);
        ButterKnife.bind(this);

        // Vi laver en variabel uid
        String uid;
        // Hvis der er en user logget ind, sættes uid til brugerens uid og vi går ind i dennes database
        if (firebaseUser != null) {
            uid = firebaseUser.getUid();
            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(uid).child("friends");
        }
        setUpFirebaseAdapter();

        mDatabaseReference.keepSynced(true); // Opdaterer og gemmer en lokal kopi

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //For the FAB button
        FloatingActionButton fab_friend = findViewById(R.id.fab_friend);
        fab_friend.setImageResource(R.drawable.ic_add_black_24dp);

        fab_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_addfriend = new Intent(getApplicationContext(), AddFriendActivity.class);
                startActivity(intent_addfriend);
            }
        });
    }

    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Friend, FriendViewHolder>(Friend.class, R.layout.friends_lists_btn, FriendViewHolder.class, mDatabaseReference) {
            @Override
            protected void populateViewHolder(FriendViewHolder viewHolder, Friend model, int position) {
                viewHolder.bindFriendsButton(model);
            }
        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    //Tilføj onLongClick så vi kan slette friends lister
    /*@Override
    public void onStart() {
        super.onStart();

        friendsDatabase = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("friends");
        ValueEventListener readFriendsListner = new ValueEventListener() {
            Friend friend = new Friend();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    friend.ID = snapshot.getKey().toString();
                    friend.username = snapshot.child("username").getValue().toString();
                    listOfFriends.add(friend);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("VT OUTPUT:", "FAIL");
            }
        };

        friendsDatabase.addValueEventListener(readFriendsListner);

        mAdapter = new mFriends(listOfFriends, this);
        mRecyclerView.setAdapter(mAdapter);
    }*/
}
