package com.example.gymapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.gymapp.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectGymCity extends AppCompatActivity {
    String city = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gym_city);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button b1 = findViewById(R.id.bProceed);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup group = findViewById(R.id.rgCities);

                int selection = group.getCheckedRadioButtonId();
                if(selection == R.id.rbNicosia)
                {
                    city = "Nicosia";
                }
                else if(selection == R.id.rbPafos)
                {
                    city = "Pafos";
                }
                else if(selection == R.id.rbFamagusta)
                {
                    city = "Famagusta";
                }
                else if(selection == R.id.rbLimassol)
                {
                    city = "Limassol";
                }
                else if(selection == R.id.rbLarnaca)
                {
                    city = "Larnaca";
                }
                Toast.makeText(getApplicationContext(), city, Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent in = new Intent(this, LoginActivity.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }

//    public void proceedToGym(View v)
//    {
//        RadioGroup group = findViewById(R.id.rgCities);
//
//        int selection = group.getCheckedRadioButtonId();
//        if(selection == R.id.rbNicosia)
//        {
//            city = "Nicosia";
//        }
//        else if(selection == R.id.rbPafos)
//        {
//            city = "Pafos";
//        }
//        else if(selection == R.id.rbFamagusta)
//        {
//            city = "Famagusta";
//        }
//        else if(selection == R.id.rbLimassol)
//        {
//            city = "Limassol";
//        }
//        else if(selection == R.id.rbLarnaca)
//        {
//            city = "Larnaca";
//        }
//        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_LONG).show();
//    }
}