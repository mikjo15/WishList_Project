package veltektrio.wishlist_project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;

public class MyWishListActivity extends AppCompatActivity {

    @BindView(R.id.itemlist_recycler_view) // Viewet i ItemListFragment
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list); //Indeholder et fragment. Dette fragment er vores itemlist
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_myWishList));

        /*--------------------- til addWish fab ----------------------------------------*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add = new Intent(getApplicationContext(), AddWishActivity.class);
                startActivity(intent_add);
            }
        });

        /* ----------------------- til recycleview -----------------------------------*/

        FragmentManager mfragmentManager = getSupportFragmentManager();
        Fragment fragment_recycle = new ItemListFragment();

        mfragmentManager.beginTransaction()
                .add(R.id.MyWishList_ItemListFragment, fragment_recycle)
                .commit();
    }

}
