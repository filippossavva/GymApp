package com.example.gymapp;



import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.gymapp.ui.login.LoginActivity;

public class ClassesFragment extends Fragment {
    private CharSequence body;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classes, container, false);
        Button b1 = root.findViewById(R.id.submit_classes);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationChannel channel = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    channel = new NotificationChannel(
                            "1",
                            "channel1",
                            NotificationManager.IMPORTANCE_DEFAULT);

                    //create the notification manager
                    NotificationManager manager = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );
                    manager.createNotificationChannel(channel);

                    //create the notification
                    NotificationCompat.Builder notification = new NotificationCompat.Builder(getActivity(), "1")
                            .setSmallIcon(android.R.drawable.btn_star)
                            .setContentTitle( "Thanks for rating our classes" )
                            .setContentText(body)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    NotificationManagerCompat notifyAdmin = NotificationManagerCompat.from(getActivity());
                    notifyAdmin.notify(1, notification.build());
                }
            }
        });
        return root;
    }
}