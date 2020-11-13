package com.example.gymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gymapp.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText name,surname,username,email,password,confPass;
    Button register;
    RadioGroup group;
    ProgressBar loading;
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    String gender = "";
    private CharSequence body;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.etName);
        surname = (EditText) findViewById(R.id.etSurname);
        username = (EditText) findViewById(R.id.etEmailAddress);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        confPass = (EditText) findViewById(R.id.etConfirmPass);
        loading = (ProgressBar) findViewById(R.id.pbLoading);
        group = (RadioGroup) findViewById(R.id.rbGroup);
        register = (Button) findViewById(R.id.button_register);


        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        fAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString().trim();
                String surnameText = surname.getText().toString().trim();
                String usernameText = username.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                String confirmPasswordText = confPass.getText().toString().trim();


                int choice = group.getCheckedRadioButtonId();
                if(choice == R.id.rbMale)
                {
                    gender = "Male";
                }
                else if(choice == R.id.rbFemale)
                {
                    gender = "Female";
                }


                if(validateName() && validateSurname() && validateUsername() && validateEmail() && validatePassword() && validateConfPass()){
                    loading.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                user information = new user(
                                        nameText,
                                        surnameText,
                                        emailText,
                                        usernameText,
                                        passwordText,
                                        gender
                                );
                                FirebaseDatabase.getInstance().getReference("user")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                                        Intent in = new Intent(getApplicationContext(), RegisterActivity.class);
                                        startActivity(in);
                                        EditText e1 = findViewById(R.id.etName);
                                        EditText e2 = findViewById(R.id.etSurname);

                                        String name = e1.getText().toString().trim();
                                        String surname = e2.getText().toString().trim();


                                        NotificationChannel channel = null;
                                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            channel = new NotificationChannel(
                                                    "1",
                                                    "channel1",
                                                    NotificationManager.IMPORTANCE_DEFAULT);

                                            //create the notification manager
                                            NotificationManager manager = getSystemService(NotificationManager.class);
                                            manager.createNotificationChannel(channel);

                                            //create the notification


                                            NotificationCompat.Builder notification = new NotificationCompat.Builder(RegisterActivity.this, "1")
                                                    .setSmallIcon(android.R.drawable.btn_star)
                                                    .setContentTitle("Congratulations " +name+ " "+surname+ "!" +"\n You are ready to log in")
                                                    .setContentText(body)
                                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                                            NotificationManagerCompat notifyAdmin = NotificationManagerCompat.from(RegisterActivity.this);
                                            notifyAdmin.notify(1, notification.build());
                                        }
                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }


            }
        });



    }

//    public void sendNotification(View v) {
//        EditText e1 = findViewById(R.id.etName);
//        EditText e2 = findViewById(R.id.etSurname);
//
//        String name = e1.getText().toString().trim();
//        String surname = e2.getText().toString().trim();
//
//
//        NotificationChannel channel = null;
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            channel = new NotificationChannel(
//                    "1",
//                    "channel1",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//
//            //create the notification manager
//            NotificationManager manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//
//            //create the notification
//
//
//            NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "1")
//                    .setSmallIcon(android.R.drawable.btn_star)
//                    .setContentTitle("Congratulations!You are ready to log in")
//                    .setContentText(body)
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//            NotificationManagerCompat notifyAdmin = NotificationManagerCompat.from(this);
//            notifyAdmin.notify(1, notification.build());
//        }
//        else {
//
//
//        }
//    }

    public boolean validateName()
    {
        EditText nameText = findViewById(R.id.etName);
        String val = nameText.getText().toString().trim();
        if (val.isEmpty()) {
            nameText.setError("Name can not be empty");
            return false;
        } else {
            nameText.setError(null);
            return true;
        }
    }
    public boolean validateSurname()
    {
        EditText surnameText = findViewById(R.id.etSurname);
        String val = surnameText.getText().toString().trim();
        if (val.isEmpty()) {
            surnameText.setError("Surname can not be empty");
            return false;
        } else {
            surnameText.setError(null);
            return true;
        }
    }
    public boolean validateUsername()
    {
        EditText usernameText = findViewById(R.id.etEmailAddress);
        String val = usernameText.getText().toString().trim();
        int count = 0;

        for(int i=0 ; i<val.length();i++)
        {
            if (Character.isDigit(val.charAt(i)))
            {
                count++;
            }
        }
        if(count == 0)
        {
            usernameText.setError("Username must contains at least one number");
            return false;
        }
        else if (val.isEmpty()) {
            usernameText.setError("Username can not be empty");
            return false;
        } else if (val.length() > 20) {
            usernameText.setError("Username is too large");
            return false;
        }else if (val.length() < 6) {
            usernameText.setError("Username must contains up to 6 characters");
            return false;
        }else {
            usernameText.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        EditText emailText = findViewById(R.id.etEmail);
        String val = emailText.getText().toString().trim();
        if (val.isEmpty()) {
            emailText.setError("Email can not be empty");
            return false;
        } else if (val.contains("@")) {
            emailText.setError("Email must contains @");
            return false;
        }else {
            emailText.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        EditText passwordText = findViewById(R.id.etPassword);
        String val = passwordText.getText().toString().trim();
        int count = 0;

        for(int i=0 ; i<val.length();i++)
        {
            if (Character.isDigit(val.charAt(i)))
            {
                count++;
            }
        }
        if(count == 0)
        {
            passwordText.setError("Password must contains at least one number");
            return false;
        }

        if (val.isEmpty()) {
            passwordText.setError("Password can not be empty");
            return false;
        }else if (val.length() < 6) {
            passwordText.setError("Password must contains up to 6 characters");
            return false;
        }
        else if (val.length() > 30) {
            passwordText.setError("Password is too large");
            return false;
        }
            else {
            passwordText.setError(null);
            return true;
        }
    }

    private boolean validateConfPass()
    {
        EditText passwordText = findViewById(R.id.etPassword);
        String val1 = passwordText.getText().toString().trim();
        EditText confPassText = findViewById(R.id.etConfirmPass);
        String val2 = passwordText.getText().toString().trim();

        int count = 0;

        for(int i=0 ; i<val1.length();i++)
        {
            if (Character.isDigit(val1.charAt(i)) )
            {
                count++;
            }
        }
        if(count == 0)
        {
            passwordText.setError("Password must contains at least one number");
            return false;
        }

        if(val1 != val2)
        {
            confPassText.setError("Passwords do not match. Please try again");
            return false;
        }
        else
        {
            confPassText.setError(null);
            return true;
        }
    }
    public void goToLogin(View v)
    {
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }
}