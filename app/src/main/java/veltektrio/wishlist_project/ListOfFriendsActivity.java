package veltektrio.wishlist_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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
    private Wishlist mWishlist = new Wishlist("Mikkel");
    private Wishlist cWishlist = new Wishlist("Cathrine");
    private Wishlist bWishlist = new Wishlist("Benjamin");

    private List<Wishlist> ListOfLists = Arrays.asList(mWishlist, cWishlist, bWishlist);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_friends);
        ButterKnife.bind(this);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new mFriends(ListOfLists, this);
        mRecyclerView.setAdapter(mAdapter);
    }

}