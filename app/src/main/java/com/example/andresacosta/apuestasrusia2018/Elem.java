package com.example.andresacosta.apuestasrusia2018;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Elem {
    public int Bet;
    public String Equip;
    public int Number;

    public Elem(){}
    public Elem(int bets, String equips, int number){
        this.Bet = bets;
        this.Equip = equips;
        this.Number = number;
    }
}
