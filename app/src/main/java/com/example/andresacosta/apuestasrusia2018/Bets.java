package com.example.andresacosta.apuestasrusia2018;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bets extends Fragment {
    private static final String TAG = "Bets";
    ArrayList selections;
    ArrayList points;
    ArrayList bet;
    ArrayList keys;
    ListView betList;
    CustomAdapterBets adapterBets;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches,container,false);
        readDatabaseEquips();
        betList = (ListView) view.findViewById(R.id.matchesList);
        adapterBets = new CustomAdapterBets();
        return view;
    }

    public void readDatabaseEquips(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("selecciones");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                selections = new ArrayList<String>();
                bet = new ArrayList<Integer>();
                points = new ArrayList<Integer>();
                keys = new ArrayList<String>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    Elem objeto = postSnapshot.getValue(Elem.class);
                    keys.add(postSnapshot.getKey());
                    selections.add(objeto.Equip);
                    bet.add(objeto.Bet);
                    points.add(objeto.Number);
                }
                betList.setAdapter(adapterBets);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    class CustomAdapterBets extends BaseAdapter{
        @Override
        public int getCount() {
            return selections.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                view = getLayoutInflater().inflate(R.layout.each_bet,null);
            }
            ImageView bandera = (ImageView)view.findViewById(R.id.flagImage);
            TextView numero = (TextView)view.findViewById(R.id.betsNumbers);
            TextView plata = (TextView)view.findViewById(R.id.betsPoints);
            bandera.setImageResource(Matches.images[i]);
            numero.setText("Cantidad de apuestas: "+bet.get(i).toString());
            plata.setText("Dinero apostado: "+points.get(i).toString());
            return view;
        }
    }
}
