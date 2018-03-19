package com.example.oguerisck.appa.Controleur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.oguerisck.appa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private TextView positionView;
    private Button buttonLogout;
    private ImageButton buttonMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user= firebaseAuth.getCurrentUser();


        positionView = (TextView) findViewById(R.id.positionView);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText(" " + user.getEmail());
        buttonLogout = (Button) findViewById(R.id.logoutButton);
        buttonMenu = (ImageButton) findViewById(R.id.menuButton);

        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
       if(v == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity( new Intent(this, LoginActivity.class));
        }
        if(v == buttonMenu){
            firebaseAuth.signOut();
            finish();
            startActivity( new Intent(this, Home.class));
        }
    }

