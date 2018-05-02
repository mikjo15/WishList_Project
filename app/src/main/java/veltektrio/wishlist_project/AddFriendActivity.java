package veltektrio.wishlist_project;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

public class AddFriendActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    private String friend_uid;
    private String friend_username;

    @BindView(R.id.addFriend_UID)
    public EditText et_UID;

    @BindView(R.id.addFriend_UserName)
    public EditText et_User;

    // Vi får fat i den bruger der er logget ind
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        ButterKnife.bind(this);

        String user_uid;
        // Hvis der er en user logget ind, sættes uid til brugerens uid og vi går ind i dennes database
        if (firebaseUser != null) {
            user_uid = firebaseUser.getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child(user_uid).child("friends");
        }

        FloatingActionButton fab_closeFriend = findViewById(R.id.fab_closeFriend);
        fab_closeFriend.setImageResource(R.drawable.ic_done_black_24dp);

        fab_closeFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_UID.getText()))
                    friend_uid = et_UID.getText().toString();
                if (!TextUtils.isEmpty(et_User.getText()))
                    friend_username = et_User.getText().toString();
                Friend newFriend = new Friend(friend_username, friend_uid);
                // friend uid tilføjet for at få onclick til at fungere i viewholder
                // Fungerer når du laver en ny ven gennem listen.
                // Klassen Friend skal indeholde id, jeg har ikke bare kunne bruge navnet


                mDatabase.child(friend_uid).setValue(newFriend).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AddFriendActivity.this, "Friend added", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(AddFriendActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
    }
}
