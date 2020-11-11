package com.example.gymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.etName);
        surname = (EditText) findViewById(R.id.etSurname);
        username = (EditText) findViewById(R.id.etUsername);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        confPass = (EditText) findViewById(R.id.etConfirmPass);
        loading = (ProgressBar) findViewById(R.id.pbLoading);
        group = (RadioGroup) findViewById(R.id.rbGroup);
        register = (Button) findViewById(R.id.button_reg);

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

                if(TextUtils.isEmpty(nameText))
                {
                    name.setError("Name is Required.");
                }
                if(TextUtils.isEmpty(surnameText))
                {
                    surname.setError("Surname is Required.");
                }
                if(TextUtils.isEmpty(usernameText))
                {
                    username.setError("Username is Required.");
                }
                if(TextUtils.isEmpty(emailText))
                {
                    email.setError("Email is Required.");
                }
                if(TextUtils.isEmpty(passwordText))
                {
                    password.setError("Password is Required.");
                }
                if(!confirmPasswordText.equals(passwordText))
                {
                    confPass.setError("Passwords do not match.");
                }
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
                                    Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(in);
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
        });



    }
    public void goToLogin(View v)
    {
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }
}