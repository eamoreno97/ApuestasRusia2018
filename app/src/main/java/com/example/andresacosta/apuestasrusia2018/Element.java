package com.example.andresacosta.apuestasrusia2018;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Element {
    public String Name;
    public String Email;
    public int Points;

    public Element(){}
    public Element(String username, String email, int points){
        this.Name = username;
        this.Email = email;
        this.Points = points;
    }
}
