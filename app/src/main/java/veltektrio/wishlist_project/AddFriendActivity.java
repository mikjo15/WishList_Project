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

public class AddFriendActivity extends AppCompatActivity {

    // Variablerne initialiseres og EditText bindes
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
        ButterKnife.bind(this); // Vi bruger Butterknife til at binde EditText
        getSupportActionBar().setTitle(getResources().getString(R.string.title_addFriend));

        // Hvis der er en user logget ind, sættes uid til brugerens uid og vi går ind i dennes database
        String user_uid;
        if (firebaseUser != null) {
            user_uid = firebaseUser.getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child(user_uid).child("friends");
        }

        // Floating action button bindes og der sættes en OnClickListener til den
        FloatingActionButton fab_closeFriend = findViewById(R.id.fab_closeFriend);
        fab_closeFriend.setImageResource(R.drawable.ic_done_black_24dp);

        fab_closeFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Der laves et nyt Friend objekt, og uid og username sættes
                if (!TextUtils.isEmpty(et_UID.getText()))
                    friend_uid = et_UID.getText().toString();
                if (!TextUtils.isEmpty(et_User.getText()))
                    friend_username = et_User.getText().toString();
                Friend newFriend = new Friend(friend_username, friend_uid);

                // Den nye ven sættes ind i Firebase og der kommer en besked om hvorvidt opgaven er løst eller ej
                mDatabase.child(friend_uid).setValue(newFriend).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AddFriendActivity.this, getResources().getString(R.string.toast_addFriend), Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(AddFriendActivity.this, getResources().getString(R.string.toast_error), Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
    }
}
