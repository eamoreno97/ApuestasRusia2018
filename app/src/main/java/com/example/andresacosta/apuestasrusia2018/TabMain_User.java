package com.example.andresacosta.apuestasrusia2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TabMain_User extends AppCompatActivity{
    Button accessButton;
    private  static final  String TAG = "TabMain";
    private SectionsPageAdapter mSectionsPageAdapter;
    ViewPager mViewPager;
    static String KeyEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
        Log.d(TAG, "oncreate: Starting");
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        Intent intent = getIntent();
        KeyEmail = intent.getStringExtra("Well");

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        accessButton = findViewById(R.id.btnAccess);
        accessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TabMain_User.this, MainActivity.class);
                String message = "Hello";
                intent.putExtra("Well",message);
                startActivity(intent);
            }
        });
    }

    private void setupViewPager (ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Matches(),"Partidos");
        viewPager.setAdapter(adapter);
    }
}
