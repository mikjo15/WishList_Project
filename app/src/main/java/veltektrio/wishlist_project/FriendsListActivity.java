package veltektrio.wishlist_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friends_list_activity, menu);
        return true;
    }

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

            startActivity(new Intent(getApplicationContext(), ListOfFriendsActivity.class));
            Toast.makeText(this, "Friend deleted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
