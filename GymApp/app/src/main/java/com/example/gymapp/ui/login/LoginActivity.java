package com.example.gymapp.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymapp.LogInHint;
import com.example.gymapp.MainActivity;
import com.example.gymapp.R;
import com.example.gymapp.RegisterActivity;
import com.example.gymapp.SelectGymCity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    boolean service;

    private LoginViewModel loginViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        Intent in = getIntent();
        Bundle info = new Bundle();
        service = info.getBoolean("service");

        final EditText usernameEditText = findViewById(R.id.etEmailAddress);
        final EditText passwordEditText = findViewById(R.id.etPassword);
        final Button loginButton = findViewById(R.id.button_login);
        final ProgressBar loadingProgressBar = findViewById(R.id.pbLoading);

        ImageView ivForgot = findViewById(R.id.ivHelpForgot);
        ImageView ivRegister = findViewById(R.id.ivHelpRegister);
        TextView helpRegister = findViewById(R.id.tvHelpRegister);
        TextView helpForgot = findViewById(R.id.tvHelpForgot);
        if(service == false)
        {
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.log_in_hint);
            ivForgot.startAnimation(anim);
            ivRegister.startAnimation(anim);
            helpForgot.startAnimation(anim);
            helpRegister.startAnimation(anim);
        }

        FloatingActionButton fab = findViewById(R.id.fabHelp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = false;
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.log_in_hint);
                ivForgot.startAnimation(anim);
                ivRegister.startAnimation(anim);
                helpForgot.startAnimation(anim);
                helpRegister.startAnimation(anim);

                Intent inS = new Intent(getApplicationContext(), LogInHint.class);
                startService(inS);
            }
        });

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    goToCityActivity();
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        TextView forgotpass = findViewById(R.id.tvForgotPass);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetemail = new EditText(v.getContext());
                AlertDialog.Builder resetPasswordDialog = new AlertDialog.Builder(v.getContext());
                resetPasswordDialog.setTitle("Reset your password");
                resetPasswordDialog.setMessage("Please enter your email to receive a reset link");
                resetPasswordDialog.setView(resetemail);

                if(service == false)
                {
                    service = true;
                    Intent in = new Intent(getApplicationContext(), LogInHint.class);
                    stopService(in);
                }

                fAuth = FirebaseAuth.getInstance();
                resetPasswordDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetemail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this,"Check your Inbox or Junk/Spam Email to find your reset link that we sent!",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Something went wrong.. We couldn't send you a reset link!" + e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                resetPasswordDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                resetPasswordDialog.create().show();
            }
        });

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
    public void goToCityActivity()
    {
        ProgressBar loadingProgressBar = findViewById(R.id.pbLoading);
        EditText email =  findViewById(R.id.etEmailAddress);
        EditText password = findViewById(R.id.etPassword);
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        loadingProgressBar.setVisibility(View.VISIBLE);
        fAuth = FirebaseAuth.getInstance();

        fAuth.signInWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_LONG).show();

                    Intent in = new Intent(LoginActivity.this, SelectGymCity.class);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Failed to login.Please check your credentials!",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(in);
                }
            }
        });
    }

    public void createAccount(View v)
    {
        if(service == false)
        {
            service = true;
            Intent in = new Intent(this, LogInHint.class);
            stopService(in);
        }
        Intent in = new Intent(this, RegisterActivity.class);
        startActivity(in);
        finish();
    }

    public void serviceStop(View v)
    {
        if(service == false)
        {
            service = true;
            Intent in = new Intent(this, LogInHint.class);
            stopService(in);
        }
    }

}