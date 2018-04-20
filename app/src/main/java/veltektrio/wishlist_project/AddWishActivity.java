package veltektrio.wishlist_project;


import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddWishActivity extends AppCompatActivity {

    private String name;
    private String size;
    private String link;
    private double price;
    private String color;
    private String shop;
    private String note;

    private DatabaseReference mDatabase;

    @BindView(R.id.editText_name)
    public EditText et_name;

    @BindView(R.id.editText_size)
    public EditText et_size;

    @BindView(R.id.editText_link)
    public EditText et_link;

    @BindView(R.id.editText_price)
    public EditText et_price;

    @BindView(R.id.editText_color)
    public EditText et_color;

    @BindView(R.id.editText_shop)
    public EditText et_shop;

    @BindView(R.id.editText_note)
    public EditText et_note;

    // Vi får fat i den bruger der er logget ind
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);
        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Vi laver en variabel uid
        String uid;
        // Hvis der er en user logget ind, sættes uid til brugerens uid og vi går ind i dennes database
        if (firebaseUser != null) {
            uid = firebaseUser.getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("wishes");
            // det sidste child bruges her til at tilgå ønskelisten
            // Hvis fragmentet skal bruges til friends, skal vi måske lave en ny databasereference, der tilgår friends
        }

        FloatingActionButton fab_add = findViewById(R.id.fab_add);
        fab_add.setImageResource(R.drawable.ic_done_black_24dp);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               System.out.println("TRYK");
                //tester om editText er tomme
                if (!TextUtils.isEmpty(et_name.getText()))
                    name = et_name.getText().toString();
                if (!TextUtils.isEmpty(et_size.getText()))
                    size = et_size.getText().toString();
                if (!TextUtils.isEmpty(et_link.getText()))
                    link = et_link.getText().toString();
                if (!TextUtils.isEmpty(et_price.getText()))
                    price = Double.parseDouble(et_price.getText().toString());
                if (!TextUtils.isEmpty(et_color.getText()))
                    color = et_color.getText().toString();
                if (!TextUtils.isEmpty(et_shop.getText()))
                    shop = et_shop.getText().toString();
                if (!TextUtils.isEmpty(et_note.getText()))
                    note = et_note.getText().toString();
                //Opretter wish med alle de felter der er

                Wish newWish = new Wish(name, size, link, price, color, shop, note);

                MyWishListActivity.myWishlist.add_wish(newWish);

                mDatabase.child(name).setValue(newWish).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AddWishActivity.this, "Wish added", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddWishActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                  finish();
            }
        });
    }

}
