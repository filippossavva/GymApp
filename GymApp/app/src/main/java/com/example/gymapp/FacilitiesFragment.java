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
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class FacilitiesFragment extends Fragment {
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    FirebaseUser user;
    CharSequence body;
    String id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_facilities, container, false);
        {
            fAuth = FirebaseAuth.getInstance();
            user = FirebaseAuth.getInstance().getCurrentUser();
            databaseReference = FirebaseDatabase.getInstance().getReference("facilities_rate");
            id = user.getUid();
            Button b1 = root.findViewById(R.id.submit_facilities);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RatingBar bar = root.findViewById(R.id.ratingBarFacilities);
                    EditText comment = root.findViewById(R.id.etCFacilities);
                    TextView title = root.findViewById(R.id.tvTitle);
//                    TextView tvGym = root.findViewById(R.id.tvGymName);

                    String object = title.getText().toString();
                    String com = comment.getText().toString();
//                    String name = tvGym.getText().toString();
                    double rate = bar.getRating();

//                    databaseReference.child(id).child("Name").setValue(name);
                    databaseReference.child(id).child("Rate").setValue(rate);
                    databaseReference.child(id).child("Object").setValue(object.toString());
                    databaseReference.child(id).child("Comment").setValue(com.toString());

                    NotificationChannel channel = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        channel = new NotificationChannel(
                                "4",
                                "channel4",
                                NotificationManager.IMPORTANCE_DEFAULT);

                        //create the notification manager
                        NotificationManager manager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                        manager.createNotificationChannel(channel);
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                R.drawable.facilities);
                        //create the notification

                        NotificationCompat.Builder notification = new NotificationCompat.Builder(getActivity(), "4")
                                .setContentTitle("Thanks for rating our facilities")
                                .setContentText(body)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setSmallIcon(android.R.drawable.btn_star_big_on)
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
}
