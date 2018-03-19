package com.example.oguerisck.appa.Modele;

/**
 * Created by Oguerisck on 04/03/2018.
 */

public class Contact {
    int id;
    String name, email, uname, pass;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUname() {
        return uname;
    }

    public String getPass() {
        return pass;
    }
}
