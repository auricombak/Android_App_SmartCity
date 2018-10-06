package com.example.oguerisck.appa.Modele;

import java.util.Date;

/**
 * Created by Oguerisck on 04/03/2018.
 */

public class Contact {
    String nom, prenom, dateNaissance, telephone;
    SettingsActualite settingsActualite;


    public Contact( String nom,  String prenom, String dateNaissance, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
    }


    public String getNom() {
        return nom;
    }


    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public SettingsActualite getSettingsActualite() {
        return settingsActualite;
    }

    public void setSettingsActualite(SettingsActualite settingsActualite) {
        this.settingsActualite = settingsActualite;
    }
}
