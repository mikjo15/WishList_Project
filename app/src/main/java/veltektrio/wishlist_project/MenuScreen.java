package veltektrio.wishlist_project;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuScreen extends AppCompatActivity {

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        Button mine_btn = findViewById(R.id.mine_btn);
        mine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String refToUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Intent mine_intent = new Intent(getApplicationContext(), MyWishListActivity.class);
                mine_intent.putExtra("refToUserID", refToUserID);
                mine_intent.putExtra("activity", "mine");
                startActivity(mine_intent);
            }
        });

        Button friends_btn = findViewById(R.id.friends_btn);
        friends_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent friends_intend = new Intent(getApplicationContext(), ListOfFriendsActivity.class);
                startActivity(friends_intend);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_wish_list_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long id = item.getItemId();
        if (id == R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            Toast.makeText(this, getResources().getString(R.string.toast_SignOut), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.copy_uid) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("UserID", firebaseUser.getUid());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, getResources().getString(R.string.toast_userID), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
