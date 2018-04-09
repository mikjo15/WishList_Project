package veltektrio.wishlist_project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        FloatingActionButton fab_closeFriend = findViewById(R.id.fab_closeFriend);
        fab_closeFriend.setImageResource(R.drawable.ic_done_black_24dp);

        fab_closeFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_closeFriend = new Intent(getApplicationContext(), ListOfFriendsActivity.class);
                startActivity(intent_closeFriend);
            }
        });
    }
}
