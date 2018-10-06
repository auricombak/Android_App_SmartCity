package com.example.oguerisck.appa.Controleur;
import com.example.oguerisck.appa.R;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRegister;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //if you want to lock screen for always Portrait mode
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        buttonLogin = (Button) findViewById(R.id.button_signin);
        textViewRegister = (TextView) findViewById(R.id.tvRegister);

        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);

        buttonLogin.setTag(0);
        textViewRegister.setTag(1);

    }

    public void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Entrez une adresse email", Toast.LENGTH_SHORT).show();
            //On stoppe la fonction d'enregistrement
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
            //On stoppe la fonction d'enregistrement
            return;
        }

        progressDialog.setMessage("Connexion...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //Start Activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                    }
                });

    }



    @Override
    public void onClick(View v){

        if( (int) v.getTag() == 0){
            userLogin();
        }
        if( (int) v.getTag() == 1){
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}



