package com.example.gymapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.gymapp.ui.login.LoginActivity;
import com.example.gymapp.ui.main.PlaceholderFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GymSelectActivity extends AppCompatActivity {
    String city = "";

//    public static final String BOX = "";
//    public static final String CLASS = "";
//    public static final String PERSONAL = "";
//    public static final String PILATES = "";
//    public static final String TABATA = "";
//    public static final String TRX = "";
//    public static final String WEIGHTS = "";
//    public static final String YOGA = "";
//    public static final String ZUMBA = "";
//    public static final String MESSAGE ="";

    FirebaseDatabase fAuth;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    int yoga,tabata,boxing,classes,weights,personal,pilates,trx,zumba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_select);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mAuth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);

        RecyclerView recycle = findViewById(R.id.recyclerview);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));

//        ImageButton imageNext = findViewById(R.id.ibGyms);
//        imageNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToFacilities();
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Make the right choice for your body!", Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent in = getIntent();
        city = in.getStringExtra(SelectGymCity.CITY);
        getSupportActionBar().setTitle(city);



        fAuth = FirebaseDatabase.getInstance();
        databaseReference = fAuth.getReference(city);

    }


    public void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<Gyms> options = new FirebaseRecyclerOptions.Builder<Gyms>()
                .setQuery(databaseReference,Gyms.class).build();

        FirebaseRecyclerAdapter<Gyms,ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Gyms, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Gyms model) {

                holder.setDetails(GymSelectActivity.this,model.getImage(),model.getTitle(),model.getUrlfb(),model.getBoxing(),model.getClasses(),model.getPersonal(),model.getPilates(),model.getTabata(),model.getTrx(),model.getWeights(),model.getYoga(),model.getZumba());

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gyms,parent,false);

                return new ViewHolder(view);
            }
        };


        firebaseRecyclerAdapter.startListening();
        RecyclerView recycle = findViewById(R.id.recyclerview);
        recycle.setAdapter(firebaseRecyclerAdapter);

        user = mAuth.getCurrentUser();
        if (user == null)
        {
            userlogout();
        }

    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selectgym, menu);
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

        else if (id == R.id.action_location) {
            Intent in = new Intent(this, SelectGymCity.class);
            startActivity(in);
            return super.onOptionsItemSelected(item);
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }


    }
    private void userlogout() {
        FirebaseAuth.getInstance().signOut();
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
        finish();
        Toast.makeText(getApplicationContext(), "Sign Out Successful!", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    public void goToFacilities(View v)
    {
        TextView tvboxing = findViewById(R.id.tvboxing);
        TextView tvclasses = findViewById(R.id.tvclasses);
        TextView tvpersonal = findViewById(R.id.tvpersonal);
        TextView tvpilates = findViewById(R.id.tvpilates);
        TextView tvtabata = findViewById(R.id.tvtabata);
        TextView tvtrx = findViewById(R.id.tvtrx);
        TextView tvweights = findViewById(R.id.tvweights);
        TextView tvyoga = findViewById(R.id.tvyoga);
        TextView tvzumba = findViewById(R.id.tvzumba);

        TextView tvurl = findViewById(R.id.tvurl);
        String url = tvurl.getText().toString();


        String feeboxing = tvboxing.getText().toString();
        boxing = Integer.parseInt(feeboxing);
                //System.out.println(boxing);

        String feeclasses = tvclasses.getText().toString();
        classes = Integer.parseInt(feeclasses);

        String feepersonal = tvpersonal.getText().toString();
        personal = Integer.parseInt(feepersonal);

        String feepilates = tvpilates.getText().toString();
        pilates = Integer.parseInt(feepilates);

        String feetabata = tvtabata.getText().toString();
        tabata = Integer.parseInt(feetabata);

        String feetrx = tvtrx.getText().toString();
        trx = Integer.parseInt(feetrx);

        String feeweights = tvweights.getText().toString();
        weights = Integer.parseInt(feeweights);

        String feeyoga = tvyoga.getText().toString();
        yoga = Integer.parseInt(feeyoga);

        String feezumba = tvzumba.getText().toString();
        zumba = Integer.parseInt(feezumba);

//        System.out.println(boxing);
//        System.out.println(classes);
//        System.out.println(personal);
//        System.out.println(pilates);
//        System.out.println(tabata);
//        System.out.println(trx);
//        System.out.println(weights);
//        System.out.println(yoga);
//        System.out.println(zumba);

        Intent intent = new Intent(this, FacilitiesClassesActivity.class);
        Bundle fee = new Bundle();
        fee.putInt("boxing",boxing);
        fee.putInt("classes",classes);
        fee.putInt("personal",personal);
        fee.putInt("pilates",pilates);
        fee.putInt("tabata",tabata);
        fee.putInt("trx",trx);
        fee.putInt("weights",weights);
        fee.putInt("yoga",yoga);
        fee.putInt("zumba",zumba);
        fee.putString("url",url);
        intent.putExtras(fee);

//        fee.putString(BOX, feeboxing);
//        fee.putString(CLASS, feeclasses);
//        fee.putString(PERSONAL, feepersonal);
//        fee.putString(PILATES, feepilates);
//        fee.putString(TABATA, feetabata);
//        fee.putString(TRX, feetrx);
//        fee.putString(WEIGHTS, feeweights);
//        fee.putString(YOGA, feeyoga);
//        fee.putString(ZUMBA, feezumba);
//        fee.putString(MESSAGE,url);
//        intent.putExtras(fee);

        startActivity(intent);


    }
}