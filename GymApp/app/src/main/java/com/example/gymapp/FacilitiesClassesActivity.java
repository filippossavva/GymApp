package com.example.gymapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.gymapp.ui.main.SectionsPagerAdapter;

public class FacilitiesClassesActivity extends AppCompatActivity {
    public static final String URL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_classes);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton instagram = findViewById(R.id.fabinsta);
        FloatingActionButton map = findViewById(R.id.fabmap);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),SocialMediaActivity.class);
                in.putExtra(URL,"https://www.facebook.com/nonstopkings8/");
                startActivity(in);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),SocialMediaActivity.class);
                in.putExtra(URL,"https://www.instagram.com/non_stopkm/?igshid=1xerc65parjdi&fbclid=IwAR0y6KYD4ljfimFsimbRKl4HnHSgkNlAntpMeXDZBQLjRfxkmJhZtaZnXKo");
                startActivity(in);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(in);
            }
        });
    }
    public void moveToWeights(View v)
    {
        Intent in = new Intent(this,WeightsActivity.class);
        startActivity(in);
    }
    public void moveToClasses(View v)
    {
        Intent in = new Intent(this,ClassesActivity.class);
        startActivity(in);
    }
    public void moveToPersonal(View v)
    {
        Intent in = new Intent(this,PersonalActivity.class);
        startActivity(in);
    }
}