package veltektrio.wishlist_project;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddWishActivity extends AppCompatActivity {

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



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);
        ButterKnife.bind(this);

        final String activity_from_intent = getIntent().getStringExtra("activity");
        mDatabase = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().toString()).child("wishes");

        FloatingActionButton fab_add = findViewById(R.id.fab_add);
        fab_add.setImageResource(R.drawable.ic_done_black_24dp);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               System.out.println("TRYK");
                Wish newWish = new Wish();
                //tester om editText er tomme
                if (!TextUtils.isEmpty(et_name.getText()))
                    newWish.setName(et_name.getText().toString());
                if (!TextUtils.isEmpty(et_size.getText()))
                    newWish.setItemSize(et_size.getText().toString());
                if (!TextUtils.isEmpty(et_link.getText()))
                    newWish.setUrl(et_link.getText().toString());
                if (!TextUtils.isEmpty(et_price.getText()))
                    newWish.setPrice(et_price.getText().toString());
                if (!TextUtils.isEmpty(et_color.getText()))
                    newWish.setColor(et_color.getText().toString());
                if (!TextUtils.isEmpty(et_shop.getText()))
                    newWish.setShop(et_shop.getText().toString());
                if (!TextUtils.isEmpty(et_note.getText()))
                    newWish.setNote(et_note.getText().toString());

                MyWishListActivity.myWishlist.add_wish(newWish);

                mDatabase.child(newWish.getName()).setValue(newWish).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            if(activity_from_intent == "edit_wish")
                                Toast.makeText(AddWishActivity.this, "Wish edited", Toast.LENGTH_LONG).show();
                            else
                            Toast.makeText(AddWishActivity.this, "Wish added", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddWishActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                  finish();
            }
        });

        //Når aktiviteten bruges til edit wish
        if(activity_from_intent == "edit_wish")
        {
            String refToWish = getIntent().getStringExtra("refToWish");
            Log.i("RefToWish", refToWish);
            DatabaseReference wishDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl(refToWish);
            wishDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String refToDetails = snapshot.getKey().toString().toLowerCase();
                        Log.i("Key from wish", refToDetails);

                        if (refToDetails.equals("name"))
                            et_name.setText(snapshot.getValue().toString());
                        else if (refToDetails.equals("itemsize"))
                            et_size.setText(snapshot.getValue().toString());
                        else if (refToDetails.equals("url"))
                            et_link.setText(snapshot.getValue().toString());
                        else if (refToDetails.equals("price"))
                            et_price.setText(snapshot.getValue().toString());
                        else if (refToDetails.equals("color"))
                            et_color.setText(snapshot.getValue().toString());
                        else if (refToDetails.equals("shop"))
                            et_shop.setText(snapshot.getValue().toString());
                        else if (refToDetails.equals("note"))
                            et_note.setText(snapshot.getValue().toString());
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }
}
