package com.example.gymapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;

public class LogInHint extends Service {
    private Timer timer = new Timer();
    private int count;
    FacilitiesClassesActivity fca = new FacilitiesClassesActivity();

    public LogInHint() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent  in, int flag, int id)
    {
        count = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                count++;
                if(count == 5)
                {
                    fca.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Don't have an account? Click on register\nForgot you password? Click to reset", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        } ,0, 1000 );

        return super.onStartCommand(in, flag, id);
    }

    public void onDestroy()
    {
        if(timer != null)
        {
            timer.cancel();
        }
        super.onDestroy();
    }
}