package com.example.oguerisck.appa.Controleur;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.oguerisck.appa.Manifest;
import com.example.oguerisck.appa.Modele.Contact;
import com.example.oguerisck.appa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

/*    private TextView textViewUserEmail;
    private TextView positionView;*/

    private Button buttonLogout;
    private Button buttonSuivant;
    private EditText telephoneUser;
    private EditText dateNaissance;
    private EditText nom;
    private EditText prenom;
    private TextView textViewUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonLogout = (Button) findViewById(R.id.deco);
        buttonSuivant= (Button) findViewById(R.id.suivant);
        telephoneUser = (EditText) findViewById(R.id.setTelephone);
        dateNaissance = (EditText) findViewById(R.id.setAnniversaire);
        nom = (EditText) findViewById(R.id.setNom);
        prenom = (EditText) findViewById(R.id.setPrenom);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText(" " + user.getEmail());
        buttonLogout.setOnClickListener(this);
        buttonSuivant.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String surname = nom.getText().toString().trim();
        String name = prenom.getText().toString().trim();
        String phoneNumber = telephoneUser.getText().toString().trim();
        String birthDate = dateNaissance.getText().toString().trim();

        Contact userInfo = new Contact(surname, name , birthDate, phoneNumber);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).child("UserInfo").setValue(userInfo);

        Toast.makeText(this, "Informations sauvegardée ", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View v) {

/*       if (v == buttonMenu) {
            finish();
            startActivity(new Intent(this, Home.class));
        }*/
        if (v == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (v == buttonSuivant) {
            saveUserInformation();
            startActivity(new Intent(this, Home.class));
/*            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));*/
        }

    }
}
