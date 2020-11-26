package com.example.gymapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Fee extends Service {
    private Timer timer = new Timer();
    private int count;
    FacilitiesClassesActivity fca = new FacilitiesClassesActivity();

    public Fee() {
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
                if(count == 10)
                {
                    fca.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "If you are not sure what to chose, there are videos on the Facilities tab and explanation for the classes on the Classes tab", Toast.LENGTH_LONG).show();
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