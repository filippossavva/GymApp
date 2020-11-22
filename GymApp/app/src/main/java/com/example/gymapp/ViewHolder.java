package com.example.gymapp;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view=itemView;
    }

    public void setDetails(Context context,String Image, String Title,String Urlfb,Long boxing,Long classes,Long personal,Long pilates,Long tabata,Long trx, Long weights,Long yoga,Long zumba, Double lat, Double lng)
    {
        TextView fbtitle = view.findViewById(R.id.tvGyms);
        ImageView fbimage = view.findViewById(R.id.ibGyms);
        TextView fburlfb = view.findViewById(R.id.tvurl);

        fbtitle.setText(Title);
        fburlfb.setText(Urlfb);

        Picasso.get().load(Image).into(fbimage);

        TextView fbboxing = view.findViewById(R.id.tvboxing);
        TextView fbclasses = view.findViewById(R.id.tvclasses);
        TextView fbpersonal = view.findViewById(R.id.tvpersonal);
        TextView fbpilates = view.findViewById(R.id.tvpilates);
        TextView fbtabata = view.findViewById(R.id.tvtabata);
        TextView fbtrx = view.findViewById(R.id.tvtrx);
        TextView fbweights = view.findViewById(R.id.tvweights);
        TextView fbyoga = view.findViewById(R.id.tvyoga);
        TextView fbzumba = view.findViewById(R.id.tvzumba);
        TextView fblat = view.findViewById(R.id.tvLat);
        TextView fblng = view.findViewById(R.id.tvLng);

        String strLat = String.valueOf(lat);
        fblat.setText(strLat);

        String strLng = String.valueOf(lng);
        fblng.setText(strLng);

        String strBox = String.valueOf(boxing);
        fbboxing.setText(strBox);

        String strClasses = String.valueOf(classes);
        fbclasses.setText(strClasses);

        String strPersonal = String.valueOf(personal);
        fbpersonal.setText(strPersonal);

        String strPilates = String.valueOf(pilates);
        fbpilates.setText(strPilates);

        String strTabata = String.valueOf(tabata);
        fbtabata.setText(strTabata);

        String strTrx = String.valueOf(trx);
        fbtrx.setText(strTrx);

        String strWeights = String.valueOf(weights);
        fbweights.setText(strWeights);

        String strYoga =String.valueOf(yoga);
        fbyoga.setText(strYoga);

        String strZumba = String.valueOf(zumba);
        fbzumba.setText(strZumba);
    }
}
