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

    public void setInformation(Context context,String Image, String Title)
    {
        TextView fbtitle = view.findViewById(R.id.tvGyms);
        ImageView fbimage = view.findViewById(R.id.ibGyms);

        fbtitle.setText(Title);

        Picasso.get().load(Image).into(fbimage);
    }
}
