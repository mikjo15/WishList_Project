package veltektrio.wishlist_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyWishListActivity extends AppCompatActivity {

    public static Wishlist myWishlist;
    private Fragment fragment_popup;
    private FragmentManager mfragmentManager;

    @BindView(R.id.itemlist_recycler_view)
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mfragmentManager = getSupportFragmentManager();
        fragment_popup = new AddWishFragment();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mfragmentManager.beginTransaction().replace(R.id.MyWishlist_ItemListFragment, fragment_popup).commit();
               //overskriv det fragment der viser cardview
                //Det samme skal gøres inden fra fragment ved add wish button. Her kan man tilgå aktivitenten og derfor altid fragment
            }
        });
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
