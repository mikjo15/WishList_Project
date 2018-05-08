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
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    // Variabler instantieres og bindes
    @BindView(R.id.buttonSignin)
    Button buttonSignin;

    @BindView(R.id.editTextEmail)
    EditText editTextMail;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.textViewSignup)
    TextView textViewSignup;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // Vi undersøger om der er en bruger registreret
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null)
            startActivity(new Intent(this, MenuScreen.class));

        // Der laves en instans af progressDialog
        progressDialog = new ProgressDialog(this);

        // onClickListener sættes for de to knapper
        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    // Funktion til når der trykkes på signin-knappen
    // Mail og password sættes og der gives en besked hvis disse er tomme
    // Der vises en loadingbar og man sendes videre til menu screen hvis man er logget in succesfuldt
    private void userLogin() {
        String mail = editTextMail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(mail)) {
            Toast.makeText(this, getResources().getString(R.string.toast_loginEmail), Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, getResources().getString(R.string.toast_loginPassword), Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage(getResources().getString(R.string.signin_progressDialog));
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), MenuScreen.class));
                        }
                    }
                });
    }

    // onClick defineres for de to knapper
    // userLogin-funktionen bruges hvis der trykkes på signin
    // Man sendes videre til registeraktiviteten hvis man trykker på signin knappen
    @Override
    public void onClick(View view) {
        if(view == buttonSignin) {
            userLogin();
        }

        if(view == textViewSignup) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}