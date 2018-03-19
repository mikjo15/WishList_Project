package veltektrio.wishlist_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Husk at koble onClick sammen med activities

public class MenuScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        Button mine_btn = findViewById(R.id.mine_btn);
        mine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mine_intent = new Intent(getApplicationContext(), MyWishListActivity.class);
                startActivity(mine_intent);
            }
        });

        Button friends_btn = findViewById(R.id.friends_btn);
        friends_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent friends_intend = new Intent(getApplicationContext(), FriendsWishListActivity.class);
                //startActivity(friends_intend);
            }
        });
    }

}
