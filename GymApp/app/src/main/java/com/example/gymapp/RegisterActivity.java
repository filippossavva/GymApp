package com.example.gymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gymapp.ui.login.LoginActivity;
import com.example.gymapp.ui.login.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null)
        {
            Intent in = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(in);
            finish();
        }
    }

    public void newUser(View v)
    {
        EditText name = findViewById(R.id.etName);
        EditText surname = findViewById(R.id.etSurname);
        EditText username = findViewById(R.id.etUsername);
        EditText email = findViewById(R.id.etEmail);
        EditText password = findViewById(R.id.etPassword);
        EditText confPass = findViewById(R.id.etConfirmPass);
        ProgressBar loading = findViewById(R.id.pbLoading);



        String nameText = name.getText().toString().trim();
        String surnameText = surname.getText().toString().trim();
        String usernameText = username.getText().toString().trim();
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String confirmPasswordText = confPass.getText().toString().trim();

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
        if(confirmPasswordText != passwordText)
        {
            confPass.setError("Passwords do not match.");
        }
        loading.setVisibility(View.VISIBLE);
        //register user
        fAuth.createUserWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void goToLogin(View v)
    {
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }
}