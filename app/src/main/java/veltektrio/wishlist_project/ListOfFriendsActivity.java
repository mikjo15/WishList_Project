package veltektrio.wishlist_project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOfFriendsActivity extends AppCompatActivity {

    @BindView(R.id.btns_RecyclerView)
    public RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Friend> listOfFriends = Arrays.asList();

    private DatabaseReference friendsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_friends);
        ButterKnife.bind(this);

        /*Old by Benjamin & Cathrine
        //Gets data from database                                                  My user ID                      List of friends
        database_friends = FirebaseDatabase.getInstance().getReference().child("PBEQIVYpWkagi7ZZq5UdlHCnnxC3").child("friends");

        ValueEventListener readFriendsListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("VT OUTPUT:", "test");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String friend = snapshot.getValue().toString();
                    Log.i("VT OUTPUT:", friend);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("VT OUTPUT:", "FAIL");
            }
        };

        database_friends.addValueEventListener(readFriendsListner);*/

        // Vi laver en variabel uid
        String uid;
        // Hvis der er en user logget ind, sættes uid til brugerens uid og vi går ind i dennes database
        if (firebaseUser != null) {
            uid = firebaseUser.getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("wishes");
            // det sidste child bruges her til at tilgå ønskelisten
            // Hvis fragmentet skal bruges til friends, skal vi måske lave en ny databasereference, der tilgår friends
        }

        mDatabase.keepSynced(true);
        friendsDatabase = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("friends");

        mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new mFriends(listOfFriends, this);
        mRecyclerView.setAdapter(mAdapter);

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

    //Tilføj onLongClick så vi kan slette friends lister

}
