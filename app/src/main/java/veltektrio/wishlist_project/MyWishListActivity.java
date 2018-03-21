package veltektrio.wishlist_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyWishListActivity extends AppCompatActivity {

    @BindView(R.id.itemlist_recycler_view)
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Wishlist testList = new Wishlist("Test wishlist");
        Wish firstWish = new Wish("name", "size", "url", 66, "note", "color", "shop");
        Wish firstWish2 = new Wish("name2", "size", "url", 66, "note", "color", "shop");
        Wish firstWish3 = new Wish("name3", "size", "url", 66, "note", "color", "shop");
        testList.add_wish(firstWish);
        testList.add_wish(firstWish2);
        testList.add_wish(firstWish3);


        adapter = new WishListAdapter(testList);
        recyclerView.setAdapter(adapter);
    }

}
