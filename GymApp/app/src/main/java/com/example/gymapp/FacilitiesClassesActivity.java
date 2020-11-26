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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.gymapp.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FacilitiesClassesActivity extends AppCompatActivity {
    public static final String URL = "";
    FirebaseAuth mAuth;
    FirebaseUser user;
    public int yoga,tabata,boxing,classes,weights,personal,pilates,trx,zumba,price;
    String url, name;
    double lat, lng;
    public static final String NAME = "";
    boolean service = false;

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
        FloatingActionButton map = findViewById(R.id.fabmap);
        FloatingActionButton rate = findViewById(R.id.fabrate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent inS = new Intent(this, Fee.class);
        startService(inS);

        mAuth = FirebaseAuth.getInstance();



        Intent in = getIntent();
        Bundle fee = in.getExtras();
        boxing = fee.getInt("boxing");
        classes = fee.getInt("com.example.gymapp.classes");
        personal = fee.getInt("personal");
        pilates = fee.getInt("pilates");
        tabata = fee.getInt("tabata");
        trx = fee.getInt("trx");
        weights = fee.getInt("weights");
        yoga = fee.getInt("yoga");
        zumba = fee.getInt("zumba");
        url = fee.getString("url");
        name = fee.getString("name");
        lat = fee.getDouble("lat");
        lng = fee.getDouble("lng");

        getSupportActionBar().setTitle(name);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle info = new Bundle();
                Intent inS = new Intent(getApplicationContext(), Fee.class);
                stopService(inS);
                Intent in = new Intent(getApplicationContext(),SocialMediaActivity.class);
                info.putString("url",url);
                info.putString("name",name);
                in.putExtras(info);
                startActivity(in);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle coord = new Bundle();
                Intent inS = new Intent(getApplicationContext(), Fee.class);
                stopService(inS);
                Intent in = new Intent(getApplicationContext(),MapsActivity.class);
                coord.putDouble("lat", lat);
                coord.putDouble("lng", lng);
                coord.putString("name",name);
                in.putExtras(coord);
                startActivity(in);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inS = new Intent(getApplicationContext(), Fee.class);
                stopService(inS);
                Intent in = new Intent(getApplicationContext(),RateActivity.class);
                in.putExtra(NAME, name);
                startActivity(in);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void moveToWeights(View v)
    {
        Intent inS = new Intent(getApplicationContext(), Fee.class);
        stopService(inS);
        Intent in = new Intent(this,WeightsActivity.class);
        startActivity(in);
    }
    public void moveToClasses(View v)
    {
        Intent inS = new Intent(getApplicationContext(), Fee.class);
        stopService(inS);
        Intent in = new Intent(this,ClassesActivity.class);
        startActivity(in);
    }
    public void moveToPersonal(View v)
    {
        Intent inS = new Intent(getApplicationContext(), Fee.class);
        stopService(inS);
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

        else
        {
            return super.onOptionsItemSelected(item);
        }

    }
    //signOut.
    private void userlogout() {
        FirebaseAuth.getInstance().signOut();
        Intent inS = new Intent(getApplicationContext(), Fee.class);
        stopService(inS);
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
        price = 0;
        CheckBox cbWeight = findViewById(R.id.cbWeights);
        CheckBox cbClass = findViewById(R.id.cbClasses);
        CheckBox cbPers = findViewById(R.id.cbPersonal);
        Switch swZum = findViewById(R.id.swZumba);
        Switch swBox = findViewById(R.id.swBoxing);
        Switch swTab = findViewById(R.id.swTabata);
        Switch swT = findViewById(R.id.swTrx);
        Switch swPil = findViewById(R.id.swPilates);
        Switch swYo = findViewById(R.id.swYoga);


        if(cbClass.isChecked())
        {
            price += classes;
        }

        if(cbWeight.isChecked())
        {
            price += weights;
        }

        if(cbPers.isChecked())
        {
            price += personal;
        }

        if(swBox.isChecked())
        {
            price += boxing;
        }

        if(swPil.isChecked())
        {
            price += pilates;
        }

        if(swTab.isChecked())
        {
            price += tabata;
        }

        if(swT.isChecked())
        {
            price += trx;
        }

        if(swYo.isChecked())
        {
            price += yoga;
        }

        if(swZum.isChecked())
        {
            price += zumba;
        }



        Toast.makeText(this, "Total monthly price: " + price +" euros." , Toast.LENGTH_LONG).show();
    }

    public void serviceStop(View v)
    {
        if(service == false)
        {
            service = true;
            Intent in = new Intent(this, Fee.class);
            stopService(in);
        }
    }

}