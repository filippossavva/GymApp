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

    public void setInformation(Context context,String Image, String Title,Long boxing,Long classes,Long personal,Long tabata, Long weights,Long yoga)
    {
        TextView fbtitle = view.findViewById(R.id.tvGyms);
        ImageView fbimage = view.findViewById(R.id.ibGyms);

        fbtitle.setText(Title);

        Picasso.get().load(Image).into(fbimage);

        TextView fbboxing = view.findViewById(R.id.tvboxing);
        TextView fbyoga = view.findViewById(R.id.tvyoga);
        TextView fbtabata = view.findViewById(R.id.tvtabata);
        TextView fbclasses = view.findViewById(R.id.tvclasses);
        TextView fbweights = view.findViewById(R.id.tvweights);
        TextView fbpersonal = view.findViewById(R.id.tvpersonal);

        String strBox = String.valueOf(boxing);
        fbboxing.setText(strBox);

        String strYoga =String.valueOf(yoga);
        fbyoga.setText(strYoga);

        String strTabata = String.valueOf(tabata);
        fbtabata.setText(strTabata);

        String strClasses = String.valueOf(classes);
        fbclasses.setText(strClasses);

        String strWeights = String.valueOf(weights);
        fbweights.setText(strWeights);

        String strPersonal = String.valueOf(personal);
        fbpersonal.setText(strPersonal);


    }
}
