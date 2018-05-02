package veltektrio.wishlist_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;

public class FriendsListActivity extends AppCompatActivity {

    private android.support.v4.app.FragmentManager mfragmentManager;
    private ItemListFragment fragment_recycle;

    @BindView(R.id.itemlist_recycler_view) // Viewet i ItemListFragment
    public RecyclerView recyclerView; // Ikke bindet i onCreate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        mfragmentManager = getSupportFragmentManager();
        fragment_recycle = new ItemListFragment();

        mfragmentManager.beginTransaction()
                .add(R.id.MyWishList_ItemListFragment, fragment_recycle)
                .commit();
    }
}
