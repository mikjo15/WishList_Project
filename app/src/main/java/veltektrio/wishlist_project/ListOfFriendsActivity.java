package veltektrio.wishlist_project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private Wishlist mWishlist = new Wishlist("Mikke");
    private Wishlist cWishlist = new Wishlist("Cathrine");
    private Wishlist bWishlist = new Wishlist("Benjamin");

    private List<Wishlist> ListOfLists = Arrays.asList();

    private DatabaseReference database_friends;
    // Datareference bruges til at koble til db. Det er egentlig bare et link til Firebase på nettet
    private DatabaseReference mDatabase;

    // Vi får fat i den bruger der er logget ind
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

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

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new mFriends(ListOfLists, this);
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

    // Alt det der står i onStart skal muligvis rykkes, så det ikke gælder for alle steder hvor
    // dette fragment bruges.
    // FirebaseRecyclerAdapter er fundet i en tutorial. Bruges til at sætte views til diverse værdier
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Wish, ItemListFragment.WishViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Wish, ItemListFragment.WishViewHolder>
                (Wish.class, R.layout.item_list_recycleview, ItemListFragment.WishViewHolder.class, mDatabase) {

        }

    }

}
