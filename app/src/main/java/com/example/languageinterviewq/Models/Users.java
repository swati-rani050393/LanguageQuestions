package com.example.languageinterviewq.Models;

public class Users {
    private String name,email,profile,pass,conpass;

    public Users() {
    }

    public Users(String name, String email, String pass, String conpass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.conpass = conpass;
    }

    public Users(String name, String email, String profile, String pass, String conpass) {
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.pass = pass;
        this.conpass = conpass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConpass() {
        return conpass;
    }

    public void setConpass(String conpass) {
        this.conpass = conpass;
    }
}
