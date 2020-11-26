package com.example.gymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gymapp.ui.login.LoginActivity;
import com.example.gymapp.ui.login.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity {
    EditText name,surname,username,email,password,confPass;
    Button register;
    RadioGroup group;
    RadioButton male,female;
    ProgressBar loading;
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    String gender = "";
    private CharSequence body;
    private String file = "gym2lift.txt";



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
        male = (RadioButton) findViewById((R.id.rbMale));
        female = (RadioButton) findViewById((R.id.rbFemale));
        register = (Button) findViewById(R.id.button_register);

        try {
            FileInputStream fin = openFileInput(file); //find file
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);

            int i=0;
            String lines[] = new String [7];
            String strLine;

            //read from buffered reader and save items into string line by line
            while((strLine = br.readLine()) != null)
            {
                lines[i] = strLine;
                i++;
            }

            name.setText(lines[0]);
            surname.setText(lines[1]);
            email.setText(lines[2]);
            username.setText(lines[3]);
            password.setText(lines[4]);
            confPass.setText(lines[5]);
            String gender = (lines[6]);
            int selection = Integer.parseInt(gender);
            if(selection == R.id.rbMale)
            {
                male.setChecked(true);
            }
            else if(selection == R.id.rbFemale)
            {
                female.setChecked(true);
            }

            fin.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

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


                if(validateName() && validateSurname() && validateUsername() && validateEmail() && validatePassword() && validateConfPass() ){
                    loading.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                try
                                {
                                    FileOutputStream fout = openFileOutput(file, 0);
                                    fout.write("".getBytes());
                                    fout.close();
                                }
                                catch (Exception ex)
                                {
                                    ex.printStackTrace();
                                }

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
                                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                                    R.drawable.logo);

                                            //create the notification manager
                                            NotificationManager manager = getSystemService(NotificationManager.class);
                                            manager.createNotificationChannel(channel);
                                            Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                                            PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(),1,login,PendingIntent.FLAG_UPDATE_CURRENT);
                                            //create the notification


                                            NotificationCompat.Builder notification = new NotificationCompat.Builder(RegisterActivity.this, "1")
                                                    .setSmallIcon(android.R.drawable.btn_star_big_on)
                                                    .setContentTitle("Congratulations " +name+ " "+surname+ "!" +"\n You are ready to log in")
                                                    .setContentText(body)
                                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                                    .setAutoCancel(true)
                                                    .setContentIntent(resultPendingIntent)
                                                    .setLargeIcon(bitmap)
                                                    .setStyle(new NotificationCompat.BigPictureStyle()
                                                            .bigPicture(bitmap)
                                                            .bigLargeIcon(null));

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
        } else if (!val.contains("@")) {
            emailText.setError("Email must contains @");
            return false;
        }else {
            emailText.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        EditText password = findViewById(R.id.etPassword);
        String val = password.getText().toString().trim();

        String passType = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@#$%^&+=])(?=\\S+$).{6,30}$";

        if(!val.matches(passType))
        {
            password.setError("Password must contains more tha 6 characters,lower case,capital case,number and symbol");
            return false;
        }
        else if(val.isEmpty())
        {
            password.setError("Field can not be empty");
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean validateConfPass()
    {
        EditText password = findViewById(R.id.etPassword);
        String val1 = password.getText().toString().trim();
        EditText conf = findViewById(R.id.etConfirmPass);
        String val2 = conf.getText().toString().trim();

        if(val2.isEmpty())
        {
            password.setError("Field can not be empty");
            return false;
        }
        else if(!val1.equals(val2))
        {
            conf.setError("Passwords do not match!");
            return false;
        }
        else
        {
            return true;
        }

    }
    public void goToLogin(View v)
    {
        EditText name = findViewById(R.id.etName);
        String nameData = name.getText().toString() + "\n";

        EditText surname = findViewById(R.id.etSurname);
        String surnameData = surname.getText().toString() + "\n";

        EditText email = findViewById(R.id.etEmail);
        String emailData = email.getText().toString() + "\n";

        EditText username = findViewById(R.id.etEmailAddress);
        String usernameData = username.getText().toString() + "\n";

        EditText password = findViewById(R.id.etPassword);
        String passwordData = password.getText().toString() + "\n";

        EditText confpassword = findViewById(R.id.etConfirmPass);
        String confpasswordData = confpassword.getText().toString() + "\n";

        RadioGroup gender = findViewById(R.id.rbGroup);
        int choice = gender.getCheckedRadioButtonId();
        String genderData = choice + "\n";

        try {
            FileOutputStream fout = openFileOutput(file,0);
            fout.write(nameData.getBytes());
            fout.write(surnameData.getBytes());
            fout.write(emailData.getBytes());
            fout.write(usernameData.getBytes());
            fout.write(passwordData.getBytes());
            fout.write(confpasswordData.getBytes());
            fout.write(genderData.getBytes());
            fout.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }



        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
        finish();
    }
}