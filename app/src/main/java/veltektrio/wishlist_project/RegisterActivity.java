package veltektrio.wishlist_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase; //reference til firebase databasen (url)
    private String user_uid;

    @BindView(R.id.buttonRegister)
    Button buttonRegister;

    @BindView(R.id.editTextEmail)
    EditText editTextMail;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.textViewSignin)
    TextView textViewSignin;

    private ProgressDialog progressDialog; //roterende cirkel når den loader (loadingskærm)

    private FirebaseAuth firebaseAuth; //Object der kan fortælle om de er logget ind eller ej

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance(); //instance er de værdier members har fået tilføjet i en instance af objektet.
        mDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextMail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, getResources().getString(R.string.toast_loginEmail), Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, getResources().getString(R.string.toast_loginPassword), Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage(getResources().getString(R.string.register_progressDialog));
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //lytter efter når task'en er udført
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            finish();

                            //adds the user to the database
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            user_uid = firebaseUser.getUid();
                            //Så fordi en key ikke kan stå uden value, sættes her name og new user. Dette er dummy værdier.
                            mDatabase.child(user_uid).child("name").setValue("new user").addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.toast_registerSuc), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.toast_registerError), Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                            startActivity(new Intent(getApplicationContext(), MenuScreen.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.toast_registerFail), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.hide();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister) {
            registerUser();
        }

        if(view == textViewSignin) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
