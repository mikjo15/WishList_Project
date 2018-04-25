package veltektrio.wishlist_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFriendActivity extends AppCompatActivity {

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        FloatingActionButton fab_closeFriend = findViewById(R.id.fab_closeFriend);
        fab_closeFriend.setImageResource(R.drawable.ic_done_black_24dp);

        fab_closeFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Friend newFriend = new Friend();

                MyWishListActivity.myWishlist.add_wish(newWish);

                mDatabase.child(name).setValue(newWish).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            if(intent_editWish.getStringExtra("refToWish") != null)
                                Toast.makeText(AddWishActivity.this, "Wish edited", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(AddWishActivity.this, "Wish added", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddWishActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                Intent intent_closeFriend = new Intent(getApplicationContext(), ListOfFriendsActivity.class);
                startActivity(intent_closeFriend);
            }
        });
    }
}
