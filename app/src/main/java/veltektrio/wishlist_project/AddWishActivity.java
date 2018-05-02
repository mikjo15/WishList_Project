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
    public EditText et_itemSize;

    @BindView(R.id.editText_link)
    public EditText et_url;

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
        final Intent intent_editWish = new Intent(getIntent());

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
               Wish newWish = new Wish();
                //tester om editText er tomme
                if (!TextUtils.isEmpty(et_name.getText()))
                    newWish.setName(et_name.getText().toString());
                if (!TextUtils.isEmpty(et_itemSize.getText()))
                    newWish.setItemSize(et_itemSize.getText().toString());
                if (!TextUtils.isEmpty(et_url.getText()))
                    newWish.setUrl(et_url.getText().toString());
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
                            if(intent_editWish.getStringExtra("refToWish") != null)
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
        if(intent_editWish.getStringExtra("refToWish") != null)
        {
            String refToWish = getIntent().getStringExtra("refToWish");
            Log.i("RefToWish", refToWish);
            DatabaseReference wishDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl(refToWish);
            wishDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String refTodetails = snapshot.getKey().toString().toLowerCase().trim();
                        Log.i("Key from wish", refTodetails);

                        if (refTodetails.equals("name"))
                            et_name.setText(snapshot.getValue().toString());
                        else if (refTodetails.equals("itemsize"))
                            et_itemSize.setText(snapshot.getValue().toString());
                        else if (refTodetails.equals("url"))
                            et_url.setText(snapshot.getValue().toString());
                        else if (refTodetails.equals("price"))
                            et_price.setText(snapshot.getValue().toString());
                        else if (refTodetails.equals("color"))
                            et_color.setText(snapshot.getValue().toString());
                        else if (refTodetails.equals("shop"))
                            et_shop.setText(snapshot.getValue().toString());
                        else if (refTodetails.equals("note"))
                            et_note.setText(snapshot.getValue().toString());
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

}
