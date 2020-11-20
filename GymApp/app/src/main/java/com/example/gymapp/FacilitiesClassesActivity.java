package com.example.gymapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.gymapp.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.gymapp.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FacilitiesClassesActivity extends AppCompatActivity {
    public static final String URL = "";
    FirebaseAuth mAuth;
    FirebaseUser user;
    int yoga,tabata,boxing,classes,weights,personal;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        Intent in = getIntent();
        Bundle fee = in.getExtras();
        yoga = fee.getInt("yoga");
        boxing = fee.getInt("boxing");
        tabata = fee.getInt("tabata");
        classes = fee.getInt("classes");
        personal = fee.getInt("personal");
        weights = fee.getInt("weights");



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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_classesfacilities, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            super.onOptionsItemSelected(item);
            userlogout();
            return true;
        }
        else if (id == R.id.action_rate) {
            Intent in = new Intent(this, RateActivity.class);
            startActivity(in);
            return super.onOptionsItemSelected(item);
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }

    }
    //signOut.
    private void userlogout() {
        FirebaseAuth.getInstance().signOut();
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
        finish();
        Toast.makeText(getApplicationContext(), "Sign Out Successful!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = mAuth.getCurrentUser();
        if (user == null)
        {
            userlogout();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void calculatefee(View v)
    {
        String message = classes+"\n" + personal;
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}