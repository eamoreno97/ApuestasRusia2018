package com.example.andresacosta.apuestasrusia2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TabMain extends AppCompatActivity{
    private  static final  String TAG = "TabMain";
    private SectionsPageAdapter mSectionsPageAdapter;
    ViewPager mViewPager;
    static String correokey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        Log.d(TAG, "oncreate: Starting");
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        Intent intent = getIntent();
        correokey = intent.getStringExtra("haha");

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager (ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Matches(),"Partidos");
        adapter.addFragment(new Bets(), "Apuestas");
        viewPager.setAdapter(adapter);
    }
}
