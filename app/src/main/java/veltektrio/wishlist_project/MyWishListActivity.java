package veltektrio.wishlist_project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyWishListActivity extends AppCompatActivity {

    public static Wishlist myWishlist;
    private Fragment fragment_popup;
    private FragmentManager mfragmentManager;
    private Fragment fragment_recycle;

    @BindView(R.id.itemlist_recycler_view) // Viewet i ItemListFragment
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter; // Kan denne ikke bare slettes?
    private RecyclerView.LayoutManager layoutManager; // Og denne

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*--------------------- til addWish ----------------------------------------*/
        mfragmentManager = getSupportFragmentManager();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("fab click to addWish");
                Intent intent_add = new Intent(getApplicationContext(), AddWishActivity.class);
                startActivity(intent_add);
            }
        });

        /* ----------------------- til recycleview -----------------------------------*/
        // Har tilføjet en bundle der sørger for det er den rigtige gren af databasen der tilgås
        Bundle bundle = new Bundle();
        bundle.putString("database", "Mine");
        fragment_recycle = new ItemListFragment();
        fragment_recycle.setArguments(bundle);
        mfragmentManager.beginTransaction()
                .add(R.id.MyWishList_ItemListFragment, fragment_recycle)
                .commit();
    }

}
