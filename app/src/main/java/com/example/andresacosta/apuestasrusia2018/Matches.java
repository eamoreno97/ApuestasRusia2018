package com.example.andresacosta.apuestasrusia2018;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Matches extends Fragment {
    private  static final  String TAG = "Partidos";
    int a;
    ArrayList emailAddress;
    ArrayList keys;
    ArrayList keys2;
    ArrayList name;
    ArrayList selecciones;
    ArrayList points;
    ArrayList bet;
    ArrayList cost;

    ArrayList<String> estadios;
    ArrayList<String> estadiosA;
    ArrayList<String> estadiosB;
    ArrayList<String> estadiosC;
    ArrayList<String> estadiosD;
    ArrayList<String> estadiosE;
    ArrayList<String> estadiosF;
    ArrayList<String> estadiosG;
    ArrayList<String> estadiosH;
    ArrayList<String> estadioTotal;

    ArrayList<String> dateA;
    ArrayList<String> dateB;
    ArrayList<String> dateC;
    ArrayList<String> dateD;
    ArrayList<String> dateE;
    ArrayList<String> dateF;
    ArrayList<String> dateG;
    ArrayList<String> dateH;
    ArrayList<String> dateTotal;

    ArrayList<String> equiposCasa;
    ArrayList<String> equiposVisita;
    ArrayList<String> nombrePaises;
    ArrayList<String> grupoBCasa;
    ArrayList<String> grupoBVisitante;
    ArrayList<String> grupoCCasa;
    ArrayList<String> grupoCVisitante;
    ArrayList<String> grupoDCasa;
    ArrayList<String> grupoDVisitante;
    ArrayList<String> grupoECasa;
    ArrayList<String> grupoEVisitante;
    ArrayList<String> grupoFCasa;
    ArrayList<String> grupoFVisitante;
    ArrayList<String> grupoGCasa;
    ArrayList<String> grupoGVisitante;
    ArrayList<String> grupoHCasa;
    ArrayList<String> grupoHVisitante;
    EditText homeNumber;
    EditText awayNumber;
    String equipo;
    String equipov;
    String llaveusuario;
    int aa;
    private DatabaseReference mDatabase;

    public static int[] images = {R.drawable.russia,R.drawable.saudi, R.drawable.egypt,R.drawable.uruguay,R.drawable.portugal,
            R.drawable.spain,R.drawable.morocco,R.drawable.iran,R.drawable.france,R.drawable.australia,R.drawable.peru,
            R.drawable.denmark,R.drawable.argentina,R.drawable.iceland,R.drawable.croatia,R.drawable.nigeria,R.drawable.brazil,
            R.drawable.switzerland,R.drawable.costarica,R.drawable.serbia,R.drawable.germany,R.drawable.mexico,R.drawable.sweden,
            R.drawable.southkorea,R.drawable.belgium,R.drawable.panama,R.drawable.tunisia,R.drawable.england,R.drawable.poland,
            R.drawable.senegal,R.drawable.colombia,R.drawable.japan};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches,container,false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        readDatabase();
        readDatabaseEquipos();


        ListView listaPartidos = (ListView) view.findViewById(R.id.matchesList);

        estadios = get_json("estadios.json","name");
        estadioTotal = get_json("grupoA.json","stadium");
        estadiosB = get_json("grupoB.json","stadium");
        estadiosC = get_json("grupoC.json","stadium");
        estadiosD = get_json("grupoD.json","stadium");
        estadiosE = get_json("grupoE.json","stadium");
        estadiosF = get_json("grupoF.json","stadium");
        estadiosG = get_json("grupoG.json","stadium");
        estadiosH = get_json("grupoH.json","stadium");
        estadioTotal.addAll(estadiosB);estadioTotal.addAll(estadiosC);estadioTotal.addAll(estadiosD);
        estadioTotal.addAll(estadiosE);estadioTotal.addAll(estadiosF);estadioTotal.addAll(estadiosG);estadioTotal.addAll(estadiosH);


        dateTotal = get_json("grupoA.json","date");
        dateB = get_json("grupoB.json","date");
        dateC = get_json("grupoC.json","date");
        dateD = get_json("grupoD.json","date");
        dateE = get_json("grupoE.json","date");
        dateF = get_json("grupoF.json","date");
        dateG = get_json("grupoG.json","date");
        dateH = get_json("grupoH.json","date");
        dateTotal.addAll(dateB);dateTotal.addAll(dateC);dateTotal.addAll(dateD);dateTotal.addAll(dateE);
        dateTotal.addAll(dateF);dateTotal.addAll(dateG);dateTotal.addAll(dateH);

        nombrePaises = get_json("teams.json","name");
        equiposCasa = get_json("grupoA.json","home_team");
        equiposVisita = get_json("grupoA.json","away_team");
        grupoBCasa = get_json("grupoB.json","home_team");
        grupoBVisitante = get_json("grupoB.json","away_team");
        grupoCCasa = get_json("grupoC.json","home_team");
        grupoCVisitante = get_json("grupoC.json","away_team");
        grupoDCasa = get_json("grupoD.json","home_team");
        grupoDVisitante = get_json("grupoD.json","away_team");
        grupoFCasa = get_json("grupoF.json","home_team");
        grupoFVisitante = get_json("grupoF.json","away_team");
        grupoECasa = get_json("grupoE.json","home_team");
        grupoEVisitante = get_json("grupoE.json","away_team");
        grupoGCasa = get_json("grupoG.json","home_team");
        grupoGVisitante = get_json("grupoG.json","away_team");
        grupoHCasa = get_json("grupoH.json","home_team");
        grupoHVisitante = get_json("grupoH.json","away_team");

        equiposCasa.addAll(grupoBCasa); equiposCasa.addAll(grupoCCasa); equiposCasa.addAll(grupoDCasa);
        equiposCasa.addAll(grupoECasa); equiposCasa.addAll(grupoFCasa); equiposCasa.addAll(grupoGCasa); equiposCasa.addAll(grupoHCasa);

        equiposVisita.addAll(grupoBVisitante); equiposVisita.addAll(grupoCVisitante); equiposVisita.addAll(grupoDVisitante);
        equiposVisita.addAll(grupoEVisitante); equiposVisita.addAll(grupoFVisitante); equiposVisita.addAll(grupoGVisitante); equiposVisita.addAll(grupoHVisitante);

        CustomAdapter adapter = new CustomAdapter();
        listaPartidos.setAdapter(adapter);
        listaPartidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View view2 = getLayoutInflater().inflate( R.layout.dialog_detail, null);
                Dialog dialog =new Dialog(getContext());
                dialog.setContentView(view2);

                ImageView casaD = (ImageView)view2.findViewById(R.id.flagsTeamA);
                casaD.setImageResource(images[Integer.parseInt(equiposCasa.get(i))]);
                equipo= nombrePaises.get(Integer.parseInt(equiposCasa.get(i).toString()));
                equipov= nombrePaises.get(Integer.parseInt(equiposVisita.get(i).toString()));
                ImageView visitaD = (ImageView)view2.findViewById(R.id.flagsTeamB);
                visitaD.setImageResource(images[Integer.parseInt(equiposVisita.get(i))]);
                dialog.show();
                homeNumber = (EditText) view2.findViewById(R.id.numberBetsA);
                awayNumber = (EditText) view2.findViewById(R.id.numberBetsB);
                TextView apostarcasa = (TextView)view2.findViewById(R.id.betHome);
                TextView apostarvisita = (TextView)view2.findViewById(R.id.betAway);
                apostarcasa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        writeNewUser(Integer.parseInt(homeNumber.getText().toString()),equipo);
                    }
                });

                apostarvisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        writeNewUser(Integer.parseInt(awayNumber.getText().toString()),equipov);
                    }
                });
            }
        });
        return view;
    }

    public void usuarioValid(){
        aa = 0;

        if (emailAddress
                .contains(TabMain.correokey.toString())){
            for (int i=0; i<emailAddress
                    .size();i++){
                if (emailAddress
                        .get(i).toString().equals(TabMain.correokey.toString())){aa=i;break;}
            }
        }
        llaveusuario = keys.get(aa).toString();
    }

    private void writeNewUser(int valor, String equipo) {
        usuarioValid();
        readDatabase();
        if (selecciones.contains(equipo)) {
            a = 0;
            for (int i = 0; i < selecciones.size(); i++) {
                if (selecciones.get(i).toString().equals(equipo)) {
                    a = i;
                    break;
                }
            }
        }
        Elem reforma = new Elem(Integer.parseInt(bet.get(a).toString())+1, equipo,100+valor);
        Element nuevaapuesta = new Element(name.get(aa).toString(), TabMain.correokey,Integer.parseInt(cost.get(aa).toString())-valor);

        if (valor <= Integer.parseInt(cost.get(aa).toString())) {
            mDatabase.child("selecciones").child(keys2.get(a).toString()).setValue(reforma);
            mDatabase.child("users").child(llaveusuario.toString()).setValue(nuevaapuesta);

            Toast.makeText(getContext(), "You have a: " + Integer.toString(Integer.parseInt(cost.get(aa).toString()) - valor) + " points.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Insuficient points", Toast.LENGTH_SHORT).show();
        }
    }

    public void readDatabaseEquipos(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("selecciones");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                selecciones = new ArrayList<String>();
                bet = new ArrayList<Integer>();

                points = new ArrayList<Integer>();
                keys2 = new ArrayList<String>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    Elem objeto2 = postSnapshot.getValue(Elem.class);
                    keys2.add(postSnapshot.getKey());
                    selecciones.add(objeto2.Equip);
                    bet.add(objeto2.Bet);
                    System.out.println(bet.size());
                    points.add(objeto2.Number);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void readDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                emailAddress = new ArrayList<String>();
                name = new ArrayList<String>();
                keys = new ArrayList<String>();
                cost = new ArrayList<Integer>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    Element objeto = postSnapshot.getValue(Element.class);
                    keys.add(postSnapshot.getKey());
                    name.add(objeto.Name);
                    emailAddress
                            .add(objeto.Email);
                    cost.add(objeto.Points);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public ArrayList<String> get_json(String string, String player){
        ArrayList<String> vector = new ArrayList<>();
        String json;
        try{
            InputStream is = getContext().getAssets().open(string);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                vector.add(obj.getString(player));
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return vector;
    }

    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return equiposCasa.size();
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

            view = getLayoutInflater().inflate(R.layout.each_match,null);
            ImageView bandera = (ImageView)view.findViewById(R.id.flagTeamA);
            ImageView bandera2 = (ImageView)view.findViewById(R.id.flagTeamB);
            TextView partido = (TextView)view.findViewById(R.id.betsNumbers);
            TextView visita = (TextView)view.findViewById(R.id.stadium);
            TextView fecha = (TextView)view.findViewById(R.id.matchDate);
            TextView estadio = (TextView)view.findViewById(R.id.group);

            fecha.setText(dateTotal.get(i).toString());
            estadio.setText(estadios.get(Integer.parseInt(estadioTotal.get(i))-1));

            bandera.setImageResource(images[Integer.parseInt(equiposCasa.get(i))]);
            bandera2.setImageResource(images[Integer.parseInt(equiposVisita.get(i))]);
            partido.setText(nombrePaises.get(Integer.parseInt(equiposCasa.get(i))));
            visita.setText(nombrePaises.get(Integer.parseInt(equiposVisita.get(i))));
            return view;
        }
    }
}
