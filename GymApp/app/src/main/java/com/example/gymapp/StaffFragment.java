package com.example.gymapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class StaffFragment extends Fragment {
    CharSequence body;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_staff, container, false);

        Button b1 = root.findViewById(R.id.submit_staff);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationChannel channel = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    channel = new NotificationChannel(
                            "3",
                            "channel3",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                            R.drawable.staffnotifications);
                    //create the notification manager
                    NotificationManager manager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                    manager.createNotificationChannel(channel);

                    //create the notification


                    NotificationCompat.Builder notification = new NotificationCompat.Builder(getActivity(), "3")
                            .setSmallIcon(android.R.drawable.btn_star_big_on)
                            .setContentTitle("Thanks for rating our staff")
                            .setContentText(body)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setLargeIcon(bitmap)
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(bitmap)
                                    .bigLargeIcon(null));

                    NotificationManagerCompat notifyAdmin = NotificationManagerCompat.from(getActivity());
                    notifyAdmin.notify(1, notification.build());
                }
            }
        });
        return root;
    }
}