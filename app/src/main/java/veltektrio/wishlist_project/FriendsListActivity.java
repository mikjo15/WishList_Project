package veltektrio.wishlist_project;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.BindView;

public class FriendsListActivity extends AppCompatActivity {

    // Variabler instantieres og bindes
    private android.support.v4.app.FragmentManager mfragmentManager;
    private ItemListFragment fragment_recycle;

    @BindView(R.id.itemlist_recycler_view)
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_friendsList));

        // Vi sætter fragmentManageren og lavet en ny instans af ItemListFragment
        mfragmentManager = getSupportFragmentManager();
        fragment_recycle = new ItemListFragment();

        mfragmentManager.beginTransaction()
                .add(R.id.MyWishList_ItemListFragment, fragment_recycle)
                .commit();
    }

    // Menuen i toolbaren sættes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friends_list_activity, menu);
        return true;
    }

    // Handlingen hvis der trykkes i menuen defineres
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long id = item.getItemId();
        if (id == R.id.delete_friend) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String friend_uid = getIntent().getStringExtra("refToUserID");
            DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference()
                    .child(uid).child("friends")
                    .child(friend_uid);
            mDatabaseRef.getRef().removeValue();

            finish();
            Toast.makeText(this, getResources().getString(R.string.toast_deleteFriend), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
