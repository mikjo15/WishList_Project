package veltektrio.wishlist_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MyWishListActivity extends AppCompatActivity {

    public static Wishlist myWishlist;
    private Fragment fragment_popup;
    private FragmentManager mfragmentManager;

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
               mfragmentManager.beginTransaction().replace(R.id.?????????, fragment_popup).commit();
               //overskriv det fragment der viser cardview
                //Det samme skal gøres inden fra fragment ved add wish button. Her kan man tilgå aktivitenten og derfor altid fragment
            }
        });

    }

}
