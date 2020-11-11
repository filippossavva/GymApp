package com.example.gymapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.gymapp.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SelectCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
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
            FirebaseAuth.getInstance().signOut();
            Intent in = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }

    public void proceedToGym(View v)
    {
        RadioGroup group = findViewById(R.id.rbGroup);
        String city = "";
        int selection = group.getCheckedRadioButtonId();

        if(selection == R.id.rbFamagusta)
        {
            city = "Famagusta";
        }
        else if(selection == R.id.rbLarnaca)
        {
            city = "Larnaca";
        }
        else if(selection == R.id.rbLimassol)
        {
            city = "Limassol";
        }
        else if(selection == R.id.rbPafos)
        {
            city = "Pafos";
        }
        else if(selection == R.id.rbNicosia)
        {
            city = "Nicosia";
        }
        TextView tv = findViewById(R.id.tvText);
        tv.setText(city);
//                Intent in = new Intent(getContext(), GymSelectActivity.class);
//                in.putExtra(CITY, city);
//                startActivity(in);
    }

}