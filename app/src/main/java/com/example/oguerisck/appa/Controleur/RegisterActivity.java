package com.example.oguerisck.appa.Controleur;

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

import com.example.oguerisck.appa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //if you want to lock screen for always Portrait mode
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);


        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText) findViewById(R.id.erEmail);
        editTextPassword = (EditText) findViewById(R.id.erPassword);
        buttonRegister = (Button) findViewById(R.id.btn_signUp);
        textViewSignin = (TextView) findViewById(R.id.tvLogin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);


        buttonRegister.setTag(0);
        textViewSignin.setTag(1);


    }

    private void registerUser(){
        String email =  editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim(); //trim enlève les espaces

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

        progressDialog.setMessage("Enregistrement...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Enregistrement non effectué, -re-essayez", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v){
        if( (int) v.getTag() == 0){
            registerUser();
        }

        if( (int) v.getTag() == 1){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

     /*   bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirm = etPassword2.getText().toString();

                if(!password.equals(passwordConfirm)){
                    //popup msg
                    Toast pass = Toast.makeText(RegisterActivity.this, "Password don't match", Toast.LENGTH_SHORT);
                    pass.show();
                }
                else{
                    Contact c = new Contact();
                    c.setEmail(email);
                    c.setName(name);
                    c.setPass(password);
                    c.setUname(username);

                    helper.insertContact(c);

                    Toast pass = Toast.makeText(RegisterActivity.this, "Agree", Toast.LENGTH_SHORT);
                    pass.show();

                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(loginIntent);
                }
            }
        });*/

}



